$(document).ready(function() {
	setTimeout(function() {
		vm.$set('resultData', {
			//bmi
			value1: function() {
				var value = vueData.urlData.formData.weight / [vueData.urlData.formData.height / 100 * vueData.urlData.formData.height / 100]
				return Number(value.toFixed(2));
			},
			//蛋白质需求量
			value2: {
				value: function() {
					var value = 0
					if(vueData.urlData.formData.sportType == 1) {
						if(vueData.urlData.formData.gender == 1) {
							value = 65
						} else if(vueData.urlData.formData.gender == 2) {
							value = 55
						}
					} else if(vueData.urlData.formData.sportType == 2) {
						value = vueData.urlData.formData.weight * 1.8
					} else if(vueData.urlData.formData.sportType == 3) {
						value = vueData.urlData.formData.weight * 1.6
					};
					return Number(value.toFixed(0));
				},
				food: function() {
					var value = vueData.resultData.value2.value();
					var array = [{
						name: '肉类',
						imgUrl: 'images/p-meat.png',
						value: (value * 150 / 25).toFixed(0) + 'g',
						info: '150g肉类含25g蛋白质',
					}, {
						name: '鸡蛋',
						imgUrl: 'images/p-egg.png',
						value: (value / 6.5).toFixed(0) + '个',
						info: '一个鸡蛋约有6.5g蛋白质',
					}, {
						name: '牛奶',
						imgUrl: 'images/p-milk.png',
						value: (value / 6.5).toFixed(0) + '盒',
						info: '一盒牛奶约有6.5g蛋白质',
					}, {
						name: '米饭',
						imgUrl: 'images/p-rice.png',
						value: (value * 300 / 24).toFixed(0) + 'g',
						info: '300g大米或面粉制品有24g蛋白质',
					}]
					return array;
				},
				product: function() {
					var value = vueData.resultData.value2.value()*0.5;
					var array = [{
						name: '乳清蛋白',
						imgUrl: 'http://v.gymmaxer.com/resources/uploads/product/20161115171831_1873.jpg',
						value: (value / 8).toFixed(0),
						show: true
					}, {
						name: '正肌能',
						imgUrl: 'http://v.gymmaxer.com/resources/uploads/product/20161115173255_7982.jpg',
						value: (value / 21).toFixed(0),
						show: vueData.urlData.formData.gender == 2 && vueData.urlData.formData.weight > 65 ? false : true
					}, {
						name: '重肌能',
						imgUrl: 'http://v.gymmaxer.com/resources/uploads/product/20161115173446_627.jpg',
						value: (value / 7).toFixed(0),
						show: (vueData.urlData.formData.gender == 1 && vueData.urlData.formData.weight > 70)||(vueData.urlData.formData.gender == 2 && vueData.urlData.formData.weight > 50) ? false : true
					}];
					return array;
				}

			},
			//基础代谢率
			value3: function() {
				var value = 0
				if(vueData.urlData.formData.gender == 1) {
					value = 66.4730 + 13.571 * vueData.urlData.formData.weight + 5.033 * vueData.urlData.formData.height - 6.7550 * vueData.urlData.formData.age
				} else if(vueData.urlData.formData.gender == 2) {
					value = 65.50955 + 9.463 * vueData.urlData.formData.weight + 1.8496 * vueData.urlData.formData.height - 4.6756 * vueData.urlData.formData.age
				}
				return Number(value.toFixed(2));
			},
			//强度运动心率
			value4: function() {
				var value1 = [220 - vueData.urlData.formData.age] * 0.6,
					value2 = [220 - vueData.urlData.formData.age] * 0.8
				return value1.toFixed(0) + '~' + value2.toFixed(0);
			},
			//标准体重
			value5: function() {
				var value = vueData.urlData.formData.height - 105;
				return Number(value.toFixed(2));
			},
			//健康体重范围
			value6: {
				min: function() {
					var value = 18.5 * [
						[vueData.urlData.formData.height / 100] * [vueData.urlData.formData.height / 100]
					];
					return Number(value.toFixed(0));
				},
				max: function() {
					var value = 23.9 * [
						[vueData.urlData.formData.height / 100] * [vueData.urlData.formData.height / 100]
					];
					return Number(value.toFixed(0));
				}
			},
			//男性健美围度
			value7: [{
				name: '胸围',
				value: function() {
					var value = vueData.urlData.formData.wristCircumference * 6.5;
					return value.toFixed(0);
				}
			}, {
				name: '臀围',
				value: function() {
					var value = vueData.urlData.formData.wristCircumference * 6.5 * 0.85;
					return value.toFixed(0);
				}
			}, {
				name: '腰围',
				value: function() {
					var value = vueData.urlData.formData.wristCircumference * 6.5 * 0.7;
					return value.toFixed(0);
				}
			}, {
				name: '大腿',
				value: function() {
					var value = vueData.urlData.formData.wristCircumference * 6.5 * 0.53;
					return value.toFixed(0);
				}
			}, {
				name: '上臂',
				value: function() {
					var value = vueData.urlData.formData.wristCircumference * 6.5 * 0.36;
					return value.toFixed(0);
				}
			}, {
				name: '小腿',
				value: function() {
					var value = vueData.urlData.formData.wristCircumference * 6.5 * 0.34;
					return value.toFixed(0);
				}
			}],
			//女健美围度
			value8: [{
				name: '胸围',
				value: function() {
					var value = vueData.urlData.formData.height * 0.515;
					return value.toFixed(0);
				}
			}, {
				name: '臀围',
				value: function() {
					var value = vueData.urlData.formData.height * 0.542;
					return value.toFixed(0);
				}
			}, {
				name: '腰围',
				value: function() {
					var value = vueData.urlData.formData.height * 0.37;
					return value.toFixed(0);
				}
			}, {
				name: '大腿',
				value: function() {
					var value = vueData.urlData.formData.height * 0.295;
					return value.toFixed(0);
				}
			}, {
				name: '上臂',
				value: function() {
					var value = vueData.urlData.formData.height * 0.145;
					return value.toFixed(0);
				}
			}, {
				name: '小腿',
				value: function() {
					var value = vueData.urlData.formData.height * 0.205;
					return value.toFixed(0);
				}
			}],
		})

	}, 300)
});