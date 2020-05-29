var vueData = {};
buijs.ready(function() {
	buiVue_new();
});

function buiVue_new() {
	vm = new Vue({
		el: '#vueBox',
		data: vueData,
		ready: vueReady,
		methods: {},
		components: {}
	});
}