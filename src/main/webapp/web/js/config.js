seajs.config({
	
	alias: {
		'util': 'js/1000funs'
	}, 
	
	// 加载 shim 插件
	plugins: ['shim'],

	shim : {
		'jquery': {
			src: 'js/jquery-1.8.0.js', 
			exports: 'jQuery'
		},
		'bootstrap' : {
			src: 'bootstrap/js/bootstrap.min.js', 
			deps: ['jquery']
		},
		'form' : {
			src: 'js/jquery.form.js', 
			deps: ['jquery']
		},
		'pagination' : {
			src: 'js/jquery.pagination.js', 
			deps: ['jquery']
		},
		'bootstrap.extension' : {
			src: 'js/jquery.bootstrap.extension.js', 
			deps: ['jquery']
		}
	}

});