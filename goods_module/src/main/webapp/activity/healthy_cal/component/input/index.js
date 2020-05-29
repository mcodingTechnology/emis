var React = require("react");
require("./style.scss");
var imgType = {

}
/**
 * label 左边的标签
 * unit 右边的单位
 * imgType
 * @type {*|Function}
 */
var Input = React.createClass({
    getInitialState(){
       return ({
          inputValue:""
       })
    },
    change(e){
        this.setState({
            inputValue:e.target.value
        })
    },
    componentWillReceiveProps(){
        this.forceUpdate();
    },
    showSelectList(){
        if(this.props.clickFun && typeof this.props.clickFun =="function"){
            this.props.clickFun();
        }
    },
    render:function(){
        var style={};
        /*var imgUrl = this.props.imgUrl;
        if(imgUrl){
            style= {
                "backgroundImage": "url('" + imgUrl + "')"
            }
        }*/
        if(this.props.size){
            style.backgroundSize=this.props.size;
        }
        var className="input bg_n "+this.props.type;
        return (
            <div>
                <div className ={className} onClick={this.showSelectList}>
                    <span>{this.props.label}</span>
                    <input type={this.props.type?this.props.type:"tel"} onChange={this.change} value={this.props.value} disabled='disabled'/>
                    <span className="unit">{this.props.unit}</span>
                    { this.props.children}
                </div>
            </div>
        )
    }
});

module.exports = Input;