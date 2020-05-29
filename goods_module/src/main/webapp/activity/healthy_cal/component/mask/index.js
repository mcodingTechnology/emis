var React = require("react");
require("./style.scss");

/**
 * label 左边的标签
 * unit 右边的单位
 * imgType
 * @type {*|Function}
 */
var Mask = React.createClass({
    hide(){
        if(this.props.clickFun && typeof this.props.clickFun =="function"){
            this.props.clickFun();
        }
    },
    render:function(){
        return (
            <div className="mask" onClick={this.hide}>
                <div className ="mask_content" >
                    <h5>{this.props.title?this.props.title :"提示"}</h5>
                    <p>{this.props.content}</p>
                    <div className="">关闭</div>
                </div>
            </div>
        )
    }
});

module.exports = Mask;