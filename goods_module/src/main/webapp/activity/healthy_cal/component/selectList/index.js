var React = require("react");
require("./style.scss");
var config  = require("./config.js");
var $ =require("jquery");
var SelectList = React.createClass({
    getInitialState(){
        return ({
            select:false
        })
    },
    componentDidMount(){
        var scroll = this.refs.selectList;
        var height = (($(window).height()/100)*8);
        scroll.scrollTop = config[this.props.type]().init*height;
    },
    change(e){
        this.setState({
            inputValue:e.target.value
        })
    },
    showSelectList(){
        this.setState({
            select:true
        });
    },
    select(item){
        if(this.props.selectFun && typeof this.props.selectFun =="function"){
            this.props.selectFun(item);
        }
    },
    hide(){
          this.setState({
            select:false
        });
        if(this.props.hideFun && typeof this.props.hideFun =="function"){
            this.props.hideFun();
        }
    },
    render:function(){
        var list = "";
        list = config[this.props.type]().list.map(function(item,index){
            return (
                <li onClick={this.select.bind(this,item)} key={index} id={item}>
                    {item}<span className="unit">{config[this.props.type]().unit}</span>
                </li>
            )
        }.bind(this));
        return (
            <div className="selectList" onClick={this.hide}>
                <div className="selectList_l"></div>
               <div className="selectList_r">
                   <div className="title"><i className="arrow arrow_l"></i>请选择</div>
                   <ul ref="selectList">{list}</ul>
               </div>
            </div>
        )
    }
});

module.exports = SelectList;