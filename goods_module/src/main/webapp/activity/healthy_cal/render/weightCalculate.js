require("../css/weightCal.scss");
var React = require("react");
var ReactDOM = require('react-dom');
var Public = require("../public");
var $  = require('jquery');
var QueryButton = require("../component/queryButton/index.js");
var Input = require("../component/input/index");
var SelectList = require("../component/selectList/index");
var infoModel= require("../model/infoModel");
var upDataInfoModel= require("../model/upDataInfoModel");
var Calculate = require("../calculateConfig.js");
var WightCaculate = React.createClass({
    model: {},
    getInitialState(){
        return ({
            type:"age",
            height:"",
            weight:"",
            showSelectList:false
        });
    },
    componentWillMount: function() {
        this.model.infoModel= infoModel;
        this.model.upDataInfoModel= upDataInfoModel;
        var _self= this;
        $.when(this.model.infoModel.getData()).then(function(data){
            var weight= data.data.ext2;  //体重
            var height= data.data.ext1;  //身高
            weight= weight.substring(0,weight.length-2);
            height= height.substring(0,height.length-2);
            _self.setState({
                weight: weight,
                height: height
            });
        });
    },
    setSelectList(type){
        this.setState({
            showSelectList:true,
            type:type
        });
    },
    select(item){
        var type = this.state.type;
        this.state[type]  =item;
        this.setState({
            showSelectList:false
        });
    },
    hide(){
        this.setState({
            showSelectList:false
        });
    },
    query:function(){
        if(!this.state.height){
            Public.js_msg({
                msg:"请输入身高",
                position:"center"
            });
            return ;
        } else if(!this.state.weight){
            Public.js_msg({
                msg:"请输入体重",
                position:"center"
            });
            return ;
        }else {
            var _self= this;
            var prams= {
                ext1: (this.state.height+"cm"),
                ext2: (this.state.weight+"kg")
            };
            $.when(this.model.upDataInfoModel.getData(prams)).then(function (data){
                if (data.code != 0 ) {
                    Public.js_msg({
                        msg:data.msg,
                        position:"center"
                    }); 
                    
                }
                var url = Public.changeURLArg(location.href,"step","2");
                location.href = url +"&height="+_self.state.height+"&weight="+_self.state.weight;
            },function(){
                var url = Public.changeURLArg(location.href,"step","2");
                location.href = url +"&height="+_self.state.height+"&weight="+_self.state.weight;
            });
        }
    },
    grouter(html){
        window.location.href= html;
    },
    render:function() {
        var step = Public.getUrlString("step");
        var needType = Public.getUrlString("needType");
        var height = Public.getUrlString("height");
        var weight = Public.getUrlString("weight");
        var sWeight = {};
        var src = "";
        if (needType == 'healthy'){
            $('title').html('健康体重范围');
        }
        if(step == "2"){
            var options = {
                height:height,
                weight:weight
            };
            sWeight =Calculate.weightCalculate(needType,options);
            sWeight.title= needType == "standard"?"您的标准体重为":"您的健康体重范围为";
            src =require( "../image/weight_result"+sWeight.type+".png");
        }
        return (
            <div className="wightCaculate">
                { step == "1" && (<div>
                    <div className="box1"></div>
                    <Input type="height" label="身高" unit="cm" imgUrl='../../image/height.png' ref="heightInput"  value={this.state.height}  clickFun={this.setSelectList.bind(this,"height")}/>
                    <div className="box2"></div>
                    <Input type="weight" label="体重" unit="kg" imgUrl='../../image/weight.png' size="6% 50%" ref="weightInput" value={this.state.weight}  clickFun={this.setSelectList.bind(this,"weight")}/>
                    <div className="box3"></div>
                    <QueryButton className="queryButton1" queryMsg="查询" queryFun={this.query}/>
                    {this.state.showSelectList  &&
                    <SelectList type={this.state.type} selectFun={this.select} hideFun={this.hide}/>}
                    </div>)
                }
                {step == "2" && (
                    <div>
                    <div className="content">
                        {sWeight.title}:&nbsp;<span className='red'>{sWeight.result}</span>&nbsp;kg
                        {/*<p>(1kg=2斤)</p>*/}
                    </div>
                        <img src={src} className='resultImg'/>
                        <div className="resultMsg">{sWeight.msg}</div>
                     <div className="footer">
                        <QueryButton className="queryButton2" queryMsg={sWeight.btnMsg} queryFun={this.grouter.bind(this,sWeight.html)}/>
                     </div>
                      </div>
                )}
            </div>)
    }
});

ReactDOM.render(
    <WightCaculate/>,
    document.getElementById("wightCaculate")
);
