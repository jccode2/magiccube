
/**
 * Button Group
 */
;(function ($) {
	
	var ButtonGroup = function (element) {
		this.$el = $(element);
		this._value0 = this.val(); //old value
	};

	ButtonGroup.prototype.val = function() {
		return $("button.active", this.$el).val();
	};

	ButtonGroup.prototype.click = function(callback) {
		var self = this;
		this.$el.click(function (e) { 
			setTimeout(function () {
				callback.call(self, e);
				self._value0 = self.val();
			}, 0);
		});
	};

	ButtonGroup.prototype.change = function(callback) {
		var self = this;
		this.click(function (e) {
			if(this.val() != this._value0) { //changed
				e.type="change";
				callback.call(self, e);
			}
		});
	};

	$.fn.buttonGroup = function () {
		return new ButtonGroup($(this));
	};

})(jQuery);


/**
 * Validation
 */
;(function ($) {

	/**
	 * Validate Type
	 * @type {object}
	 */
	var Type = {
		NOT_NULL: "notNull", 
		FILE_NOT_NULL: "fileNotNull"
	}, 

	validate_selector = "[data-validate]";

	/**
	 * Constructor
	 * @param {object} options 
	 */
	var Validation = function (options) {
		this.options = $.extend({}, $.validation.defaults, options);
	};

	Validation.prototype = {

		constructor: Validation, 

		init: function () {
			var $validate = $(validate_selector);
			$validate.tooltip({
				trigger: "manual"
			})
			.on("validate", function() {
				_doValidate(this);
			});

			if(this.options.onfocus) {
				$validate.focus(function() {
					_doValidate(this);
				});
			}

			if(this.options.onblur) {
				$validate.blur(function() {
					_doValidate(this);
				});
			};

			return this;
		}, 

		check: function() {
			// $(validate_selector).trigger("validate");
			var pass = true;
			$(validate_selector).each(function (idx, el) {
				if(!_doValidate(el)) pass = false;
			});
			return pass;
		}
	};

	function _doValidate(el) {
		$el = $(el);
		if(!_isPass($el)) {
			$el.tooltip('show');
			return false;
		}
		else {
			$el.tooltip('hide');
			return true;
		}
	}

	/**
	 * check whether pass the validation
	 * @param  {jQuery} $el 
	 * @return {boolean}     pass:true; nopass:false;
	 */
	function _isPass($el) {
		var type = $el.attr("data-validate") || Type.NOT_NULL;
		switch(type) {
			case Type.NOT_NULL: 
				return !!$el.val();
				break;
			case Type.FILE_NOT_NULL:
				return !!$el.get(0).files[0];
				break;

			// use customer funtion
			default:
				try {
					return eval(type);
				} catch(e) {};
		}
	}

	/**
	 * PLUGIN DEFINITION
	 * @param  {object} options 
	 */
	$.validation = function (options) {
		var $doc = $(document), 
			instance = $doc.data('validation');
		// singleton
		if(!instance) $doc.data('validation', (instance = new Validation(options)));
		return instance;
	};

	$.validation.defaults = {
		onfocus: false, 
		onblur: true
	};

	$.validation.Constructor = Validation;

})(jQuery);

