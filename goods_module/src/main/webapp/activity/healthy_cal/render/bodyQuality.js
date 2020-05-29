require('../css/bodyQuality.scss');
var React = require("react");
var Public = require("../public");
var ReactDOM = require('react-dom');
var $= require('jquery');
var QueryButton = require("../component/queryButton/index.js");
var Input = require("../component/input/index");
var SelectList = require("../component/selectList/index");
var Calculate = require("../calculateConfig.js");
var infoModel= require("../model/infoModel");
var upDataInfoModel= require("../model/upDataInfoModel");
var BodyQuality = React.createClass({

    model: {},

    getInitialState(){
       return ({
           sex:"boy",
           type:"age",
           age:"",
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
    //选择性别
    choiceSex:function(type,e){
        e.preventDefault();
        this.setState({
            sex:type
        });
    },
    setSelectList: function(type){
        this.setState({
            showSelectList:true,
            type:type
        });
    },
    select: function(item){
        var type = this.state.type;
        this.state[type]  =item;
        this.setState({
            showSelectList:false
        });
    },
    hide: function(){
        this.setState({
            showSelectList:false
        });
    },
    query: function(){
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
    ask: function(html){
        window.location.href = html;
    },
    render:function(){
        var step = Public.getUrlString("step");
        var BMI = "";
        var src = "../image/BMI_result1.png";
        if(step =="2"){
            var  height = Public.getUrlString("height"),
                weight = Public.getUrlString("weight");
            var options = {
                height :height,
                weight:weight
            };
            BMI =Calculate.BMI(options);
            src = require("../image/BMI_result"+BMI.type+".png");
        }

        return (
            <div className="">
                { step == "1" && (<div className="BMI1">
                    <div className="qry">
                        <div className="relative h41">
                            <Input type="height" label="身高" unit="cm" imgUrl='../../image/height.png' ref="heightInput" value={this.state.height} clickFun={this.setSelectList.bind(this,"height")}/>
                            <div className="box4"></div>
                        </div>
                        <Input type="weight" label="体重" unit="kg" imgUrl='../../image/weight.png' size="6% 50%" ref="weightInput" value={this.state.weight} clickFun={this.setSelectList.bind(this,"weight")}/>
                        <div className="box3"></div>
                     </div>
                    <QueryButton className="queryButton1" queryMsg="查询" queryFun={this.query}/>
                    {this.state.showSelectList  &&
                             <SelectList type={this.state.type} selectFun={this.select} hideFun={this.hide}/>}
                </div>)
                }
                {
                    step =="2" &&(
                        <div className="BMI1">
                            <div className="content">
                                您的BMI:&nbsp;<span className='red'>{BMI.result}</span>
                            </div>
                            <div className="h5v"></div>
                            <img src={src} className='resultImg'/>
                            <div className="BMI_resultMsg resultMsg">{BMI.msg}</div>
                            <div className="footer">
                                <QueryButton className="queryButton2" queryMsg={BMI.btnMsg} queryFun={this.ask.bind(this,BMI.html)}/>
                            </div>
                        </div>
                    )
                }
            </div>
        )
    }
});

ReactDOM.render(<BodyQuality />,
    document.getElementById("bodyQuality")
);
