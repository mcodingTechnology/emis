var config ={
    age:function(){
        var age = {
            unit:"岁",
            init:30
        };
        var list = [];
        for(var i=0;i<=150;i++){
            list.push(i);
        }
        age.list = list;
        return age;
    },
    height:function(){
        var height = {
            unit:"cm",
            init:150
        };
        var list = [];
        for(var i=0;i<=250;i++){
            list.push(i);
        }
        height.list = list;
        return height;
    },
    weight:function() {
        var weight = {
            unit: "kg",
            init: 100
        };
        var list = [];
        for (var i = 0; i <= 200; i=i+0.5) {
            list.push(i);
        }
        weight.list = list;
        return weight;
    },
    hand:function(){
        var hand = {
            unit:"cm",
            init:20
        };
        var list = [];
        for(var i=0;i<=50;i++){
            list.push(i);
        }
        hand.list = list;
        return hand;
    },
    sport:function(){
        var sport = {
            unit:"",
            init:0
        };
        var list = ["不运动","健美增肌","其他运动"];
        sport.list = list;
        return sport;
    }

};
module.exports = config;