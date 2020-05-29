var React = require("react");
require("./style.scss");

/**
 * 类名是queryButton1为深红色按钮样式，queryButton2是浅红色的
 * @type {*|Function}
 */
var QueryButton = React.createClass({
    query:function(){
        if(this.props.queryFun && typeof this.props.queryFun =="function"){
            this.props.queryFun();
        }
    },
    render:function(){
        return(
            <div className = {this.props.className} onClick={this.query}>
                {this.props.queryMsg}
            </div>
        )
    }
});

module.exports = QueryButton;