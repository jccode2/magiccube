define(function(require, exports, module) {
	
	var $ = require('jquery'), 
		util = require('util'), 
		webRoot = util.webRoot;
    require('bootstrap');
	require('bootstrap.extension');

    // Init
	var Init = (function($) {

        function getRowContent($tr) {
            var id = $tr.attr("id") || 0, 
                content = $(".content input", $tr).val(),
                sort = $(".sort input", $tr).val() || 0,
                enable = $(":checkbox", $tr).is(":checked");
            return {
                "id": id, 
                "content": content,
                "sort": sort,
                "enable": enable
            };
        }


        // new one row
        function insertOneRow() {
            $("#bulletin_list tbody").append($("#tpl_edit_row").html());
        }

        function deleteOneRow($btn) {
            var $tr = $btn.closest("tr"),
                id = $tr.attr("id");
            if(id) {
                // delete from db
                var url = webRoot + "/shop/bulletin/" + id;
                $.ajax({
                    "url": url,
                    "type": "delete"
                })
                .done(function(){
                    $tr.remove();
                });
            } else {
                $tr.remove();
            }
        }

        function saveOneRow($btn) {
            var $tr = $btn.closest("tr"), 
                vo = getRowContent($tr);
            
            var fieldsToChecked = [
                $(".content input", $tr), 
                $(".sort input", $tr)
            ];
            if(!$.validation().check(fieldsToChecked)) {
                return ;
            }

            // save to db.
            saveBulletin(vo, function(data) {

                $tr.attr("id", data);
                $(".content", $tr).html(vo.content);
                $(".sort", $tr).html(vo.sort);
                $(".enable :checkbox", $tr).attr("disabled", "disabled");

                // change btn style
                $(".btn-save", $tr).removeClass().addClass("icon-edit btn-edit");
            });
        }


        function editOneRow($btn) {
            var $tr = $btn.closest("tr");
            var $tdContent = $(".content", $tr),
                $tdSort = $(".sort", $tr);
            $tdContent.html($("<input type='text'>").val($tdContent.html()));
            $tdSort.html($("<input type='text'>").val($tdSort.html()));
            $(".enable :checkbox", $tr).removeAttr("disabled");

            // change btn style
            $(".btn-edit", $tr).removeClass().addClass("icon-ok btn-save");
        }

        function saveBulletin(vo, callback) {
            var url = webRoot + "/shop/bulletin";
            $.post(url, vo, callback, "json").fail(function(xhr) {
                alert("save failed. error msg:\n" + xhr.responseText);
            });
        }


		
		return {
			// main
			run: function() {
				for(var fn in Init) {
					if(fn === "run" || fn.startsWith('_')) continue;
					Init[fn].call(window);
				}
			},

            btnEvent: function() {
                $("#btn_insert").on("click", insertOneRow);
                $(document).on("click", ".btn-remove", function() {
                    deleteOneRow($(this));
                });
                $(document).on("click", ".btn-save", function() {
                    saveOneRow($(this));
                });
                $(document).on("click", ".btn-edit", function() {
                    editOneRow($(this));
                });
            },

            test: function() {
                
            }
        };
    })(jQuery);
    
    
    // main
	jQuery(function ($) {
		Init.run();
	});
});
