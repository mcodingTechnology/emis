require('../css/baseMetabolize.scss');
var React = require("react");
var $ = require("jquery");
var Public = require("../public");
var ReactDOM = require('react-dom');
var QueryButton = require("../component/queryButton/index.js");
var Input = require("../component/input/index");
var SelectList = require("../component/selectList/index");
var Calculate = require("../calculateConfig.js");
var infoModel= require("../model/infoModel");
var upDataInfoModel= require("../model/upDataInfoModel");
var BaseMetabolize = React.createClass({

    model: {

    },

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
            var age= new Date().getFullYear() - new Date(data.data.birthday).getFullYear();
            var weight= data.data.ext2;  //体重
            var height= data.data.ext1;  //身高
            weight= weight.substring(0,weight.length-2);
            height= height.substring(0,height.length-2);
            _self.setState({
                weight: weight,
                height: height,
                sex: (data.data.gender && data.data.gender == "男" ? "boy": "girl"),
                age: age
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
        if(!this.state.age){
            Public.js_msg({
                msg:"请输入年龄",
                position:"center"
            });
            return ;
        }else if(!this.state.height){
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
                ext2: (this.state.weight+"kg"),
                gender: _self.state.sex == "boy"? "男": "女"
            };
            $.when(this.model.upDataInfoModel.getData(prams)).then(function (data){
                if (data.code != 0 ) {
                    Public.js_msg({
                        msg:data.msg,
                        position:"center"
                    }); 
                }
                var url = Public.changeURLArg(location.href,"step","2");
                location.href = url + "&age="+_self.state.age+"&sex="+_self.state.sex+"&height="+_self.state.height+"&weight="+_self.state.weight;
            },function(){
                var url = Public.changeURLArg(location.href,"step","2");
                location.href = url + "&age="+_self.state.age+"&sex="+_self.state.sex+"&height="+_self.state.height+"&weight="+_self.state.weight;
            });
        }
    },
    render:function(){
        var step = Public.getUrlString("step");
        var baseMe = "";
        var boyClass  = this.state.sex=="boy"?"sexSelect sexChoice":"sexSelect";
        var girlClass = this.state.sex=="girl"?"sexSelect sexChoice":"sexSelect";
        if(step =="2"){
            var age = Public.getUrlString("age"),
                sex = Public.getUrlString("sex"),
                height = Public.getUrlString("height"),
                weight = Public.getUrlString("weight");
            baseMe =Calculate.baseMetabolize(age,sex,height,weight);
        }
        var Src = require("../image/heartRate_result1.png");
        return (
            <div className="">
                { step == "1" && (<div className="baseMe">
                    <div className="qry">
                        <div className="box1"></div>
                        <div className="relative h41">
                            <Input type="age" label="年龄" unit="岁" imgUrl='../../image/age.png' size="6% 50%" ref="ageInput" value={this.state.age} clickFun={this.setSelectList.bind(this,"age")}/>
                            <div className="box4"></div>
                        </div>
                        <div className="relative h41">
                            <div className="commonBox border_register sexImg">
                                <span>性别</span>
                                <p className="commonBox-right sex">
                                    <span className={boyClass} id="boy" onClick={this.choiceSex.bind(this,"boy")}><i className="fa fa-mars"></i> 男</span>
                                    <span className={girlClass} id="girl" onClick={this.choiceSex.bind(this,"girl")}><i className="fa fa-venus"></i> 女</span>
                                </p>
                            </div>
                            <div className="box4"></div>
                        </div>
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
                        <div className="baseMe">
                            <div className="content">
                                您的基础带谢率为:&nbsp;&nbsp;<span className='red'>{baseMe}</span>
                            </div>
                            <div className="h5v"></div>
                            <div className='relative'>
                                <div className="content_title">
                                    <img src={Src} />
                                    营养师有话说:</div>
                                <div className="content_body">
                                    1、基础代谢消耗的能量占能量总消耗量的70%左右，因此，较高的基础代谢让你更不容易发胖。
                                    <br/>
                                    <br/>
                                    2、你可以通过以下方法来提高你的基础代谢率：<br/>
                                    ①增加抗阻力训练，增加肌肉量<br/>
                                    ②少食多餐，增加蛋白质摄入
                                  </div>
                            </div>
                        </div>
                    )
                }
            </div>
        )
    }
});

ReactDOM.render(<BaseMetabolize />,
    document.getElementById("baseMetabolize")
);
