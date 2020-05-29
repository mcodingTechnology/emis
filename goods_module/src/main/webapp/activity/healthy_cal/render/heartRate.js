var React =require("react");
var ReactDOM = require('react-dom');
var Public = require("../public.js");
var $= require('jquery');
var QueryButton = require("../component/queryButton/index.js");
var Input = require("../component/input/index");
var SelectList = require("../component/selectList/index");
var infoModel= require("../model/infoModel");
var upDataInfoModel= require("../model/upDataInfoModel");
var Calculate = require("../calculateConfig");

require("../css/heartRate.scss");
var HeartRate = React.createClass({
    model: {},
    getInitialState(){
        return ({
            age:"",
            showSelectList:false,
            type:"age"
        });
    },
    componentWillMount: function() {
        this.model.infoModel= infoModel;
        this.model.upDataInfoModel= upDataInfoModel;
        var _self= this;
        $.when(this.model.infoModel.getData()).then(function(data){
            var age= new Date().getFullYear() - new Date(data.data.birthday).getFullYear();
            _self.setState({
                age: age
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
        if(this.state.age){
            var url = Public.changeURLArg(location.href,"step","2");
            location.href = url + "&age="+this.state.age;
        } else {
            Public.js_msg({
               msg:"请输入年龄",
               position:"center"
            });
        }
    },
    render:function(){
        var step = Public.getUrlString("step");
        var heartRate = "";
        if(Public.getUrlString("age")){
            heartRate = Calculate.heartRate(Public.getUrlString("age"));
        }
        var Src = require("../image/heartRate_result1.png");
        return (
            <div className="heartRate">
                { step =="1" && (<div>
                    <div className="box1"></div>
                    <Input type="age" label="年龄" unit="岁" imgUrl='../../image/age.png' size="6% 50%" ref="ageInput" value={this.state.age} clickFun={this.setSelectList.bind(this,"age")}/>
                    <div className="box3"></div>
                    <QueryButton className="queryButton1" queryMsg="查询" queryFun={this.query}/>
                    {this.state.showSelectList  &&
                    <SelectList type={this.state.type} selectFun={this.select} hideFun={this.hide}/>}
                </div>)}
                {
                    step =="2" && (<div>
                        <div className="content">
                            您适宜的运动心率是:  <span className='red'>{heartRate}</span>
                        </div>
                        <div className="h5v"></div>
                        <div className='relative'>
                            <div className="content_title">
                                <img src= {Src}/>
                                营养师有话说:</div>
                            <div className="content_body">1、减肥运动需要达到适宜的运动心率，并维持40分钟左右，才能达到较好的效果。
                                <br/><br/>
                                2、一般来说，快走、慢跑、跳绳、上坡、爬山等运动能达到这个心率。
                                <br/>
                                <br/>
                                3、建议有条件的最好能配备个心率表。如果没有心率表，可以用10秒心率测算法：运动完马上测，当前的运动心率=10秒脉搏跳动次数*6
                            </div>
                        </div>
                    </div>)
                }
            </div>
        )
    }
});

ReactDOM.render(
    <HeartRate/>,
    document.getElementById("heartRate")
);