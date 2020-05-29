var React =require("react");
var ReactDOM = require('react-dom');
var Public = require("../public.js");
var QueryButton = require("../component/queryButton/index.js");
var Input = require("../component/input/index");
var SelectList = require("../component/selectList/index");
var Calculate = require("../calculateConfig.js");
require("../css/manFit.scss");
var ManFit = React.createClass({
    getInitialState(){
        return ({
            hand:"",
            showSelectList:false,
            type:"hand"
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
        if(this.state.hand){
            var url = Public.changeURLArg(location.href,"step","2");
            location.href = url + "&hand="+this.state.hand;
        } else {
            Public.js_msg({
                msg:"请输入手腕围度",
                position:"center"
            });
        }
    },
    sport(){
        var url = Public.changeURLArg(location.href,"step","3");
        location.href = url;
    },
    render:function(){
        var step = Public.getUrlString("step");
        var manFit = {};
        if(Public.getUrlString("hand") && step=="2"){
            manFit = Calculate.manFit(Public.getUrlString("hand"));
        }
        return (
            <div className="manFit">
                { step =="1" && (<div className="pdt8">
                    <div className="box1"></div>
                    <Input type="hand" label="手腕" unit="cm" imgUrl='../../image/age.png' size="6% 50%" ref="handInput" value={this.state.hand} clickFun={this.setSelectList.bind(this,"hand")}/>
                    <div className="box3"></div>
                    <QueryButton className="queryButton1" queryMsg="查询" queryFun={this.query}/>
                    {this.state.showSelectList  &&
                    <SelectList type={this.state.type} selectFun={this.select} hideFun={this.hide}/>}
                </div>)}
                {
                    step =="2" && (<div className="manFit_result">
                        <h5>您的身体各围度的标准值</h5>
                        <ul >
                            <li>胸围：<span className="red">{manFit.chest}</span></li>
                            <li>臀部：<span className="red">{manFit.hip}</span></li>
                            <li>腰围：<span className="red">{manFit.waist}</span></li>
                            <li>大腿：<span className="red">{manFit.thigh}</span></li>
                            <li>上臂：<span className="red">{manFit.arm}</span></li>
                            <li>小腿：<span className="red">{manFit.calf}</span></li>
                            <li>前臂：<span className="red">{manFit.forearm}</span></li>
                        </ul>
                        <div className="resultMsg">嘿，你身体的各项围度达到这个标准了吗？如果还没有，就努力去达到它吧！如果超标了，欢迎加入减脂大军～
                        </div>
                        <div className="footer">
                            <QueryButton className="queryButton2" queryMsg="我要训练围度" queryFun={this.sport}/>
                        </div>
                    </div>)
                }
                {
                    step == "3" && (
                        <div>
                            <ul className="manFit_result2">
                                <li>
                                    <a href="http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401464941&idx=1&sn=4fc20231798fe959da636f98a721ec14#rd">我要练胸围</a>
                                    <i className="arrow"></i>
                                </li>
                                <li>
                                    <a href="http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401458179&idx=1&sn=f47a76e45338cf249c24badacbfa29ec#rd">我要练手</a>
                                    <i className="arrow"></i>
                                </li>
                                <li>
                                    <a href="http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401461605&idx=1&sn=bb4c1da089f30dfab2d4c63fe5fba631#rd">我要练背</a>
                                    <i className="arrow"></i>
                                </li>
                                <li>
                                    <a href="http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401456269&idx=1&sn=21eb57113837c2eec76677c26b96474f#rd">我要练腹肌</a>
                                    <i className="arrow"></i>
                                </li>
                                <li>
                                    <a href="http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401458529&idx=1&sn=46fdcf298a32d42ca4ccb66817a93d00#rd">我要练臀</a>
                                    <i className="arrow"></i>
                                </li>
                                <li>
                                    <a href="http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401459557&idx=1&sn=203f4fc1c714a10ad43742e0f7629ee8#rd">我要瘦腿</a>
                                    <i className="arrow"></i>
                                </li>
                            </ul>
                        </div>
                    )
                }
            </div>
        )
    }
});

ReactDOM.render(
    <ManFit/>,
    document.getElementById("manFit")
);