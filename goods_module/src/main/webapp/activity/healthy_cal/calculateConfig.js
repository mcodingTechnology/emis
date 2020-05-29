/*
* 计算器的配置文件
* */
var config = {
    //计算中低强度运动心率
    heartRate:function(age){
       //计算公式 （220-年龄）*（60%~80%）
        var num = 1;
        var min = ((220-age)*0.6).toFixed(num);
        var max = ((220-age)*0.8).toFixed(num);
        return min+"-"+max;
    },
    //男性健美围度计算
    manFit:function(hand){
        /*胸围＝手腕*6.5
        臀部＝手腕*6.5 *0.85
        腰围＝手腕*6.5 *0.7
        大腿＝手腕*6.5 *0.53
        上臂＝手腕*6.5 *0.36
        小腿＝手腕*6.5 *0.34
        前臂＝手腕*6.5 *0.29*/
        var manFit ={};
        var num = 0;
        manFit.chest = (hand *6.5).toFixed(num);
        manFit.hip = (hand *6.5*0.85).toFixed(num);
        manFit.waist = (hand *6.5*0.7).toFixed(num);
        manFit.thigh = (hand *6.5*0.53).toFixed(num);
        manFit.arm = (hand *6.5*0.36).toFixed(num);
        manFit.calf = (hand *6.5*0.34).toFixed(num);
        manFit.forearm = (hand *6.5*0.29).toFixed(num);
        return manFit;
    },
    //女性健美围度计算
    womanFit:function(height){
        /*胸围=身高*0.515
        臀部=身高*0.542
        腰围=身高*0.37
        大腿=身高*0.295
        上臂=身高*0.145
        小腿=身高*0.205*/
        var womanFit ={};
        var num = 0; //保留位数
        womanFit.chest = (height *0.515).toFixed(num);
        womanFit.hip = (height *0.542).toFixed(num);
        womanFit.waist = (height *0.37).toFixed(num);
        womanFit.thigh = (height *0.295).toFixed(num);
        womanFit.arm = (height *0.145).toFixed(num);
        womanFit.calf = (height *0.205).toFixed(num);
        womanFit.forearm = (height *6.5*0.29).toFixed(num);
        return womanFit;
    },
    //基础代谢率计算
    baseMetabolize:function(age,sex,height,weight){
        /*男BMR=66.4730+13.571*体重（kg）+5.033*身高（cm）-6.7550*年龄（岁）
        女BMR=65.50955+9.463*体重（kg）+1.8496*身高（cm）-4.6756*年龄（岁）*/
        var baseMetabolize =0;//保留位数
        var num = 0;//保留位数
        if(sex =="boy"){
            baseMetabolize = (66.4730 + 13.571*weight + 5.033*height -6.7550*age).toFixed(num);
        } else {
            baseMetabolize = (655.0955 + 9.463*weight + 1.8496*height -4.6756*age).toFixed(num);
        }
        return baseMetabolize;
    },
    //BMI计算
    BMI:function(options){
        var BMI = {};
        var num =1; //保留位数
        var type = "1";//1，瘦  2，标准  3，超重  4，肥胖
        var btnMsg = "我要增肌";
        var html = "http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401371006&idx=1&sn=e1e091f0d2a2283ec7aebdfa84ff9c14#rd";
        var msg = "消瘦：哎呀，你简直就是根豆芽菜，太苗条了，再长点肉会更健美哦！";
        var result = (options.weight*10000 / (options.height*options.height)).toFixed(num);
        if(result >= 18.5 && result <=23.9){
            type = "2";
            msg = "正常：体重在正常范围内，继续保持良好的饮食习惯哦~";
            btnMsg = "我要更好";
            html = "http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401380042&idx=1&sn=f86c395ed5f9f2987a15f9334931e6e6#rd";
        }else if(result >23.9 && result <=27.9){
            type = "3";
            msg = "超重：哟~兄弟，你看起来像个茄子。体重有点超标啦，赶紧来把赘肉消灭掉！";
            btnMsg = "我要减脂";
            html = "http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401380483&idx=1&sn=2a6cdb2fedbb02c1a713fea7c9e77561#rd";
        }else if(result>28){
            type = "4";
            msg = "肥胖：亲，不管你是自带了游泳圈还是偷着胖的“隐形胖纸”，都应该动起来，减掉多余赘肉啦！";
            btnMsg = "我要减脂";
            html ="http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401380483&idx=1&sn=2a6cdb2fedbb02c1a713fea7c9e77561#rd";
        }
        BMI.type = type;
        BMI.btnMsg = btnMsg;
        BMI.msg = msg;
        BMI.result = result;
        BMI.html = html;
        return BMI;
    },
    weightCalculate:function(type,options){
        var sWeight = {};
        if(type == "standard"){
            var num =1; //保留位数
            var type = "1";//1，瘦  2，标准  3，超重
            var btnMsg = "我要增肌增重";
            var msg = "你的体重不达标，好瘦喔！太苗条可不符合现代审美观念，再长点肉，有肌才会更健美哦！";
            var html = "http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401371006&idx=1&sn=e1e091f0d2a2283ec7aebdfa84ff9c14#rd";
            var result = (options.height-105).toFixed(num);
            if(options.weight >= 0.9*result && options.weight <= 1.1*result){
                type = "2";
                msg = "你的体重在正常范围内，越接近标准体重越好哦！另外，体重正常但体型不一定完美，比如看起来有点瘦，或稍微有些胖，做些增肌减脂的训练能让你变得更好哦！";
                btnMsg = "我要更好";
                html = "http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401380042&idx=1&sn=f86c395ed5f9f2987a15f9334931e6e6#rd";
            }else if(options.weight >result *1.1){
                type = "3";
                msg = "你的体重超标了喂！这个状态的你，看起来像个茄子，在你身上已看不到该有的线条，一胖毁所有啊！你是要练练练呢？还是要练练练呢？";
                btnMsg = "我要减脂塑形";
                html ="http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401380483&idx=1&sn=2a6cdb2fedbb02c1a713fea7c9e77561#rd";
            }
            sWeight.type = type;
            sWeight.btnMsg = btnMsg;
            sWeight.msg = msg;
            sWeight.result = result;
            sWeight.html= html;
        } else {
            //计算公式：健康体重范围=18.5*[身高(m)]²～23.9*[身高(m)]²
            var num =1; //保留位数
            var type = "1";//1，瘦  2，标准  3，超重
            var btnMsg = "我要增肌增重";
            var msg = "你的体重还没到健康体重范围，好瘦喔！太苗条可不符合现代审美观念，再长点肉，有肌才会更健美哦！";
            var html = "http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401371006&idx=1&sn=e1e091f0d2a2283ec7aebdfa84ff9c14#rd";
            var min = (options.height*options.height*18.5/10000).toFixed(num);
            var max = (options.height*options.height*23.9/10000).toFixed(num);
            if(options.weight >=min && options.weight <=max){
                type = "2";
                msg = "你的体重在健康体重范围内，不过，你的体型可不一定完美，比如看起来有点瘦，或稍微有些胖，做些增肌减脂的训练能让你变得更好哦！";
                btnMsg = "我要更好";
                html = "http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401380042&idx=1&sn=f86c395ed5f9f2987a15f9334931e6e6#rd";
            }else if(options.weight >max){
                type = "3";
                msg = "你的体重超出健康体重范围了喂！这个状态的你，看起来像个茄子，在你身上已看不到该有的线条，你是要练练练呢？还是要练练练呢？";
                btnMsg = "我要减脂塑形";
                html ="http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401380483&idx=1&sn=2a6cdb2fedbb02c1a713fea7c9e77561#rd";
            }
            sWeight.type = type;
            sWeight.btnMsg = btnMsg;
            sWeight.msg = msg;
            sWeight.result = min+"-"+max;
            sWeight.html= html;
        }
        return sWeight;
    },
    //蛋白质需要量的计算
    protein:function(options){
        var protein ={};
        var result ;
        var msg = "";
        var btnMsg = "方便补充蛋白质";
        var type="0"; // 0 男非运动 1 女非运动 3健美 4其他运动
        if(options.sport == "1"){
            if(options.sex =="boy"){
                result = 65;
                msg = "65g蛋白质相当于10个鸡蛋，或10盒牛奶，或350g牛肉。很难吃到这么多吧？那就补充乳清蛋白粉吧。";
            } else {
                type = "1";
                result = 55;
                msg = "55g蛋白质相当于9个鸡蛋，或9盒牛奶，或300g牛肉。很难吃到这么多吧？那就补充乳清蛋白粉吧。";
            }
        } else if(options.sport =="2"){
            type="2";
            result = 1.8*options.weight;
        } else  if(options.sport=="3"){
            type="3";
            result = 1.6*options.weight;
        }
        protein.type = type;
        protein.btnMsg = btnMsg;
        protein.msg = msg;
        protein.result = result;
        return protein;
    },
    getTip:function(type){
        var msg = "";
        var defaults ={
            "weightCalculateS":"健康体重范围：简单评估你的体重是否在健康范围内。不过会有少部分人即使体重正常的，但体脂肪超标了，即隐形肥胖，也应注意多运动。",
            "weightCalculateH":"标准体重：评价是否肥胖的指标，最为简单、方便、易懂。标准体重可以作为你努力达到的体重目标，体重越接近标准体重越好哦！",
            "baseMetabolize":"基础代谢：指人体维持生命的所有器官所需的最低能量需要。基础代谢消耗的能量占能量总消耗量的70%左右，因此，较高的基础代谢让你更不容易发胖哦。",
            "heartRate":"适宜运动心率：有氧运动（中低强度）需要达到适宜的运动心率，并维持40分钟左右，才能达到较好的效果。",
            "protein":"蛋白质需要量：你每天需要吃多少蛋白质呢？不同性别，不同运动方式的人是不一样的哦，来测测吧！",
            "bodyQuality":"BMI：是最常用，方便简单判断体型的指标。注意BMI不适合下列人群：18岁以下，运动员、肌肉特别发达者、孕妇、哺乳妇女、体弱或需久坐的老人。",
            "manFit":"男性身体健美围度：你身体各个部位的围度决定了你的体型是否美观，所以不要仅仅关注你的体重，围度才是王道哦！不过要想达到健美围度的标准可是有难度的，需要你付出更多的努力和汗水！",
            "womanFit":"女性身体健美围度：你身体各个部位的围度决定了你的体型是否美观，所以不要仅仅关注你的体重，围度才是王道哦！不过要想达到健美围度的标准可是有难度的，需要你付出更多的努力和汗水！"
        };

        return defaults[type];
    },
    getProductInfo: function (weight) {
        //各产品需要多少勺
        var productInfo= {
            ruQingDanBai: {
                content: '',             //叙述
                count: 0,                //共多少勺
                breakfast: 0,            //早餐多少勺
                morningAddEat: 0,        //上午加餐多少勺
                middleAddEat: 0,         //下午加餐多少勺
                afterSport: 0,           //运动后多少勺
                beforSleep: 0            //睡前加餐多少勺
            },
            zhengJiNeng: {
                content: '',             
                count: 0,
                breakfast: 0,
                morningAddEat: 0,
                middleAddEat: 0,
                afterSport: 0,
                beforSleep: 0
            },
            zhongJiNeng: {
                content: '',
                count: 0,
                breakfast: 0,
                morningAddEat: 0,
                middleAddEat: 0,
                afterSport: 0,
                beforSleep: 0
            }
        };
        if ( 40<=weight && weight<= 45) {
            productInfo.dosage= 12;
            productInfo.ruQingDanBai= {
                content: '共2勺，运动后:2勺',
                count: 2,
                afterSport:2
            };
            productInfo.zhengJiNeng= {
                content: '共0.5勺，运动后:0.5勺',
                count: 0.5,
                afterSport: 0.5
            };
            productInfo.zhongJiNeng= {
                content: '共2勺，运动后:2勺',
                afterSport: 2,
                count: 2
            };
        }else if (45<weight && weight<= 50) {
            productInfo.dosage= 22;
            productInfo.ruQingDanBai= {
                content: '共3勺，运动后:3勺',
                count: 3,
                afterSport:3
            };
            productInfo.zhengJiNeng= {
                content: '共1勺，运动后:1勺',
                count: 1,
                afterSport: 1
            };
            productInfo.zhongJiNeng= {
                content: '共4勺，上午加餐：2勺，运动后：2勺',
                count: 4,
                morningAddEat: 2,
                afterSport: 2
            };
        }else if (50<weight && weight<= 55) {
            productInfo.dosage= 31;
            productInfo.ruQingDanBai= {
                content: '共3.5勺，上午加餐：1.5勺，运动后：2勺',
                count: 3.5,
                morningAddEat: 1.5,
                afterSport: 2
            };
            productInfo.zhengJiNeng= {
                content: '共1.5勺，运动后：1.5勺',
                count: 1.5,
                afterSport: 1.5
            };
            productInfo.zhongJiNeng= {
                content: '共5勺，上午加餐：2.5勺，运动后：2.5勺',
                count: 5,
                morningAddEat: 2.5,
                afterSport: 2.5
            };
        }else if (55<weight && weight<= 60) {
            productInfo.dosage= 40;
            productInfo.ruQingDanBai= {
                content: '共5勺，上午加餐：2勺，运动后：3勺',
                count: 5,
                morningAddEat: 2,
                afterSport: 3
            };
            productInfo.zhengJiNeng= {
                content: '共2勺，上午加餐：1勺，运动后：1勺',
                count: 2,
                morningAddEat: 1,
                afterSport: 1
            };
            productInfo.zhongJiNeng= {
                content: '共7勺，上午加餐：2勺，下午加餐：2勺，运动后：3勺',
                count: 7,
                morningAddEat: 2,
                middleAddEat: 2,
                afterSport: 3
            };
        }else if (60<weight && weight<= 65) {
            productInfo.dosage= 49;
            productInfo.ruQingDanBai= {
                content: '共6勺，上午加餐：3勺，运动后：3勺',
                count: 6,
                morningAddEat: 3,
                afterSport: 3
            };
            productInfo.zhengJiNeng= {
                content: '共2.5勺，上午加餐：1勺，运动后：1.5勺',
                count: 2.5,
                morningAddEat: 1,
                afterSport: 1.5
            };
        }else if (65<weight && weight<= 70) {
            productInfo.dosage= 58;
            productInfo.ruQingDanBai= {
                content: '共7.5勺，上午加餐：2.5勺，下午加餐：2勺，运动后：3勺',
                count: 7.5,
                morningAddEat: 2.5,
                middleAddEat: 2,
                afterSport: 3
            };
            productInfo.zhengJiNeng= {
                content: '共3勺，上午加餐：1勺，运动后：2勺',
                count: 3,
                morningAddEat: 1,
                afterSport: 2
            };
        }else if (70<weight && weight<= 75) {
            productInfo.dosage= 67;
            productInfo.ruQingDanBai= {
                content: '共8.5勺，上午加餐：2.5勺，下午加餐：3勺，运动后：3勺',
                count: 8.5,
                morningAddEat: 2.5,
                middleAddEat: 3,
                afterSport: 3
            };
            productInfo.zhengJiNeng= {
                content: '共3.5勺，上午加餐：1.5勺，运动后：2勺',
                count: 3.5,
                morningAddEat: 1.5,
                afterSport: 2
            };
        }else if (75<weight && weight<= 80) {
            productInfo.dosage= 76;
            productInfo.ruQingDanBai= {
                content: '共9.5勺，上午加餐：3勺，下午加餐：3勺，运动后：3.5勺',
                count: 9.5,
                morningAddEat: 3,
                middleAddEat: 3,
                afterSport: 3.5
            };
            productInfo.zhengJiNeng= {
                content: '共4勺，上午加餐：1勺，运动后：2勺，睡前加餐：1勺',
                count: 4,
                morningAddEat: 1,
                afterSport: 2,
                beforSleep: 1
            };
        }else if (80<weight && weight<= 85) {
            productInfo.dosage= 85;
            productInfo.ruQingDanBai= {
                content: '共10.5勺，早餐：2勺，上午加餐：2勺，下午加餐：3勺，运动后：3勺',
                count: 10.5,
                breakfast: 2,
                morningAddEat: 2,
                middleAddEat: 3,
                afterSportl: 3
            };
            productInfo.zhengJiNeng= {
                content: '共4.5勺，上午加餐：1.5勺，运动后：2勺，睡前加餐：1勺',
                count: 4.5,
                morningAddEat: 1.5,
                afterSportl: 2,
                beforSleep: 1
            };
        }else if (85<weight && weight<= 90) {
            productInfo.dosage= 94;
            productInfo.ruQingDanBai= {
                content: '共12勺，早餐：3勺，上午加餐：3勺，下午加餐：3勺，运动后：3勺',
                count: 12,
                breakfast: 3,
                morningAddEat: 3,
                middleAddEat: 3,
                afterSport: 3
            };
            productInfo.zhengJiNeng= {
                content: '共5勺，上午加餐：1勺，下午加餐：1勺，运动后：2勺，睡前加餐：1勺',
                count: 5,
                morningAddEat: 1,
                middleAddEat: 1,
                afterSport: 2,
                beforSleep: 1
            };
        }else if (90<weight && weight<= 95) {
            productInfo.dosage= 103;
            productInfo.ruQingDanBai= {
                content: '共13勺，早餐：3勺，上午加餐：3勺，下午加餐：3勺，运动后：4勺',
                count: 13,
                breakfast: 3,
                morningAddEat: 3,
                middleAddEat: 3,
                afterSport: 4
            };
            productInfo.zhengJiNeng= {
                content: '共5.5勺，上午加餐：1.5勺，下午加餐：1勺，运动后：2勺，睡前加餐：1勺',
                count: 5.5,
                morningAddEat: 1.5,
                middleAddEat: 1,
                afterSport: 2,
                beforSleep: 1
            };
        }else if (95<weight && weight<= 100) {
            productInfo.dosage= 112;
            productInfo.ruQingDanBai= {
                content: '共14勺，早餐：3.5勺，上午加餐：3.5勺，下午加餐：3勺，运动后：4勺',
                count: 14,
                breakfast: 3.5,
                morningAddEat: 3.5,
                middleAddEat: 3,
                afterSport: 4
            };
            productInfo.zhengJiNeng= {
                content: '共6勺，上午加餐：1.5勺，下午加餐：1.5勺，运动后：2勺，睡前加餐：1勺',
                count: 6,
                morningAddEat: 1.5,
                middleAddEat: 1.5,
                afterSport: 2,
                beforSleep: 1
            };
        }
        return productInfo;
    }
};
module.exports =config;