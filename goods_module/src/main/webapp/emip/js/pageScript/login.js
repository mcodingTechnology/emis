buijs.ready(function() {
	buijs.formcheck.add($('form'), {
		success: function() {
			buijs.mask.add({
				type: 'loading'
			});
			setTimeout(function() {
				window.location.href = 'index.html'
			}, 300);
		}
	})
});