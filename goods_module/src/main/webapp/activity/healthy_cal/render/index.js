require("../css/index.scss");
var Public = require("../public.js");
var React = require("react");
var ReactDOM = require('react-dom');
var menuList  = [{
    index:1,
    type:'bodyQuality',
    name:"BMI",
    imgUrl:"./../image/homePage5.png"
},{
    index:2,
    type:'protein',
    name:"蛋白质需要量",
    imgUrl:"./../image/homePage6.png"
},{
    index:3,
    type:'baseMetabolize',
    name:"基础代谢计算",
    imgUrl:"./../image/homePage7.png"
},{
    index:4,
    type:'heartRate',
    name:"强度运动心率",
    imgUrl:"./../image/homePage8.png"
},{
    index:5,
    type:'weightCalculate',
    needType:"standard",
    name:"标准体重计算",
    imgUrl:"./../image/homePage1.png"
},{
    index:6,
    type:'weightCalculate',
    needType:"healthy",
    name:"健康体重范围",
    imgUrl:"./../image/homePage2.png"
},{
    index:7,
    type:'manFit',
    name:"男性健美围度",
    imgUrl:"../image/homePage4.png"
},{
    index:8,
    type:'womanFit',
    name:"女性健美围度",
    imgUrl:"./../image/homePage3.png"
}];
var HomePage = React.createClass({
    getInitialState(){
        return ({
            menuList:menuList
        })
    },
    jump(item,e){
        e.preventDefault();
        var needType = "";
        if(item.needType){
            needType = "&needType="+item.needType;
        }
        location.href = "./"+item.type+".html?step=1"+needType;
    },
    render:function(){
        var list = this.state.menuList.map(function(item,index){
           // var src = "../image/"+item.imgUrl+".png";
            var imgClass = item.index%2 ==0?"img2 bg_n":"img1 bng_n";
            imgClass +=" homePage"+(index+1);
           /* var style ={
             "backgroundImage":"url('"+item.imgUrl+"')"
             };*/
            return (
                <li key={index}  className={imgClass} onClick={this.jump.bind(this,item)}>
                    <span>{item.name}</span>
                </li>
            )
        }.bind(this));
        return (
            <div className="homePage">
                <ul>
                    {list}
                </ul>
            </div>
        )
    }
});

ReactDOM.render(
    <HomePage/>,
    document.getElementById("homePage")
);
