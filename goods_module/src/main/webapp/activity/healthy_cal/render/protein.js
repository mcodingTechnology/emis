require('../css/protein.scss');
var React = require("react");
var Public = require("../public");
var ReactDOM = require('react-dom');
var QueryButton = require("../component/queryButton/index.js");
var Input = require("../component/input/index");
var SelectList = require("../component/selectList/index");
var Calculate = require("../calculateConfig");
var infoModel= require("../model/infoModel");
var upDataInfoModel= require("../model/upDataInfoModel");
var getLastProduct= require("../model/getLastProduct");
var $ = require('jquery');

var Protein = React.createClass({

    model: {},

    getInitialState(){
        return ({
            sex:"boy",
            type:"weight",
            weight:"",
            sport:"",
            sportType:"",
            showSelectList:false,
            LastProduct: '0'
        });
    },

    componentWillMount: function (){
        this.model.infoModel= infoModel;
        this.model.upDataInfoModel= upDataInfoModel;
        this.model.getLastProduct= getLastProduct;
        var _self= this;
        $.when(this.model.infoModel.getData()).then(function(data){
            var weight= data.data.ext2;  //体重
            weight= weight.substring(0,weight.length-2);
            var sportType= data.data.exerciseType || '',
                sport;
            switch(sportType){
                case 1: 
                    sport= "不运动";
                    break;
                case 2:
                    sport= "健美增肌";
                    break;
                case 3:
                    sport= "其他运动";
                    break;
                default:
                    break;
            }
            _self.setState({
                weight: weight,
                sex: (data.data.gender && data.data.gender == "男" ? "boy": "girl"),
                sportType: sportType,
                sport: sport
            });
        });
        $.when(this.model.getLastProduct.getData()).then(function(data){
            if (data.status == 0) {
                _self.setState({
                    LastProduct: data.data.productName
                });
            }
        })
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
        var result = 1;
        this.state[type]  =item;
        if(type=="sport"){
            if(item=="健美增肌"){
                result =2;
            } else if(item == "其他运动"){
                result = 3;
            }
            this.state.sportType = result;
        }
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

        if(!this.state.weight){
            Public.js_msg({
                msg:"请输入体重",
                position:"center"
            });
            return ;
        } else if(!this.state.sport){
            Public.js_msg({
                msg:"请输入运动类型",
                position:"center"
            });
            return ;
        }else {
            var _self= this;
            var prams= {
                gender: _self.state.sex == "boy"? "男": "女",
                ext2: _self.state.weight+"kg",
                exerciseType: _self.state.sportType
            }
            $.when(this.model.upDataInfoModel.getData(prams)).then(function (data){
                if (data.code != 0 ) {
                    Public.js_msg({
                        msg:data.msg,
                        position:"center"
                    }); 
                }
                var url = Public.changeURLArg(location.href,"step","2");
                location.href = url +"&sport="+_self.state.sportType+"&weight="+_self.state.weight+"&sex="+_self.state.sex;
            },function(){
                var url = Public.changeURLArg(location.href,"step","2");
                location.href = url +"&sport="+_self.state.sportType+"&weight="+_self.state.weight+"&sex="+_self.state.sex;
            });
        }
    },
    render:function(){
        var step = Public.getUrlString("step");
        var boyClass  = this.state.sex=="boy"?"sexSelect sexChoice":"sexSelect";
        var girlClass = this.state.sex=="girl"?"sexSelect sexChoice":"sexSelect";
        var protein = {};
        var productInfo;
        var className ="protein bg ov_a ";
        if(step =="2"){
            var sex = Public.getUrlString("sex"),
                sport = Public.getUrlString("sport"),
                weight = Public.getUrlString("weight");
            var options = {
                sex:sex,
                sport:sport,
                weight:weight
            };
            protein =Calculate.protein(options);
            if(sex =="girl"){
                className +="protein_bg1";
            }else {
                className +="protein_bg2";
            }
            productInfo= Calculate.getProductInfo(options.weight);
        }
        this.state.LastProduct.indexOf("乳清蛋白营养强化粉")
        var Src = require("../image/heartRate_result1.png");
        return (
            <div className={className}>
                { step == "1" && (<div >
                    <div className="qry">
                        <div className="box1"></div>
                        <div className="relative h41">
                            <Input type="weight" label="体重" unit="kg" imgUrl='../../image/weight.png' size="6% 50%" ref="weightInput" value={this.state.weight} clickFun={this.setSelectList.bind(this,"weight")}/>
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
                        <Input type="sport" label="运动类型" unit="" imgUrl='../../image/sport.png' size="6% 50%" ref="sportInput" value={this.state.sport} clickFun={this.setSelectList.bind(this,"sport")}>
                          <i className="arrow arrow_d"></i>
                        </Input>
                        <div className="box3"></div>
                    </div>
                    <QueryButton className="queryButton1" queryMsg="查询" queryFun={this.query}/>
                    {this.state.showSelectList  &&
                    <SelectList type={this.state.type} selectFun={this.select} hideFun={this.hide}/>}
                </div>)
                }
                {
                    step =="2" &&(<div>
                            <div className="content">
                                你每天需要吃到&nbsp;<span className='red'>{protein.result.toFixed(1)}</span>g的蛋白质哦！
                            </div>
                            <div className="h5v"></div>
                            <div className='relative'>
                                {(protein.type != "0" && protein.type != "1") && (<div><div className="content_title">
                                                                                                    <img src={Src} />
                                                                                                    蛋白粉类产品推荐:
                                                                                                </div>
                                                                                                <div className="content_body">
                                                                                                    <ul className="protein_result">
                                                                                                        <p className="productTips">补充剂来源的蛋白质量：{productInfo.dosage}g</p>
                                                                                                        <p className="productTips">以下产品服用量为单一产品服用量，非总和。</p>
                                                                                                        { (this.state.LastProduct == '0' || this.state.LastProduct.indexOf("乳清蛋白固体饮料") !='-1') && (productInfo.ruQingDanBai.count !=0 && (<li className="ruQingDanBai bg_n productli"><span style={{display: "block"}}>乳清蛋白粉</span><span className="gray">{productInfo.ruQingDanBai.content}</span></li>))}
                                                                                                        { (this.state.LastProduct == '0' || this.state.LastProduct.indexOf("乳清蛋白高纤粉") != "-1") && (productInfo.zhengJiNeng.count !=0 && (<li className="zhengJiNeng bg_n productli"><span style={{display: "block"}}>正肌能</span><span className="gray">{productInfo.zhengJiNeng.content}</span></li>))}
                                                                                                        { (this.state.LastProduct == '0' || this.state.LastProduct.indexOf("乳清蛋白营养强化粉") !='-1') && (productInfo.zhongJiNeng.count !=0 && (<li className="zhongJiNeng bg_n productli"><span style={{display: "block"}}>重肌能</span><span className="gray">{productInfo.zhongJiNeng.content}</span></li>))}
                                                                                                    </ul>
                                                                                                </div>
                                                                                                </div>)}
                                <div className="content_title">
                                    <img src={Src} />
                                    营养师有话说:</div> 
                                <div className="content_body">
                                    {(protein.type == "2"||protein.type=="3") && (<ul className="protein_result">
                                        <li className="chick bg_n">150g<span className="gray">含25克蛋白质</span></li>
                                        <li className="egg bg_n">1个<span className="gray">含6.5克蛋白质</span></li>
                                        <li className="cow bg_n">1盒<span className="gray">含6.5克蛋白质</span></li>
                                        <li className="rich bg_n">300g<span className="gray">含6.5克蛋白质</span></li>
                                     </ul>
                                    )}
                                    {protein.type == "0" && (<ul className="protein_result">
                                            <li className="chick bg_n">350g<span className="gray">65g</span></li>
                                            <li className="egg bg_n">10个<span className="gray">65g</span></li>
                                            <li className="cow bg_n">10盒<span className="gray">65</span></li>
                                        </ul>
                                    )}
                                    {protein.type == "1" && (<ul className="protein_result">
                                            <li className="chick bg_n">300g<span className="gray">65g</span></li>
                                            <li className="egg bg_n">9个<span className="gray">65g</span></li>
                                            <li className="cow bg_n">9盒<span className="gray">65</span></li>
                                        </ul>
                                    )}
                                </div>
                                
                                { (protein.type=="0"||protein.type=="1" )&&<div className="resultMsg">{protein.msg}
                                </div>}
                                { (protein.type=="2" || protein.type=="3")&&<div className="resultMsg">
                                    1、一般而言，吃150g鱼肉、或鸡肉、或猪瘦肉可以得到约25g蛋白质，一个鸡蛋约有6.5g蛋白质，一杯牛奶约有6.5g蛋白质，吃20g黄豆或相当量的豆制品有7g蛋白质，还有300g大米或面粉制品有24g蛋白质，总量约70g。如果你一天中没吃全这些食物或量没达到，蛋白质较难得到满足，需要额外补充。
                                    <br/><br/>
                                    2、优质蛋白摄入量最好能占总蛋白的1/3～1/2，食物中的鸡蛋、牛奶、鱼肉、瘦牛肉、豆类等是不错的选择。健美增肌人群，运动后及时补充快速吸收的优质蛋白，如乳清蛋白粉，可促进肌肉合成。
                                    <br/><br/>
                                </div>}
                            </div>
                        </div>
                    )
                }
            </div>
        )
    }
});

ReactDOM.render(<Protein />,
    document.getElementById("protein")
);
