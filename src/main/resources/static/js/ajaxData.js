ajaxData = function () {

    var data = {};
    const INPUT = "input";
    const SELECT = "select";
    return {
        /**
         * 获取指定容器下所有input的值组成json
         * @param selector 指定容器的id
         * @returns {{}}
         */
        getInputDataById: function (selector) {
            var inputs = $(selector).find(INPUT);
            inputs.map(function (index, item) {

                var key = item.getAttribute("name");
                var value = item.value;
                data[key] = value;

            })

            return data;
        },

        /**
         * 将后台的数据渲染到对应的input上
         * @param selector  选择所有input的父级容器
         * @param data      后台数据
         */
        setInputDataById: function (selector, data) {
            var inputs = $(selector).find(INPUT);
            inputs.map(function (index, item) {
                var key = item.getAttribute("name");
                var value = data[key];
                item.value = value;
            })
        },

        /**
         * 根据后台返回值显示下拉框对应选项
         * @param selector  下拉框的id
         * @param data       后台的数据
         */
        setSelectValue: function (selector, data) {
            var selects = $(selector).find(SELECT);
            selects.map(function (index, item) {
                var options = item.options;
                var name = item.name;
                var value = data[name];
                for (var i = 0; i < options.length; i++) {
                    if (options[i].value == value) {
                        options[i].setAttribute("selected", "selected");
                    }
                }

            })

        },
        /**
         * ajax渲染下拉框选项
         * @param selector  要渲染的下拉框
         * @param url       数据来源的url
         * @param field     要展示在下拉框选项的信息对应在返回信息对应字段名称
         */
        getSelectOption: function (selector, url, field) {
            $.ajax({
                type: 'GET',
                url: url,
                dataType: 'json',
                contentType: "application/json",
                success: function (res) {
                    $(res).each(function (i) {
                        var item = res[i];
                        $(selector).append("<option value='" + item.id + "'>" + item[field] + "</option>");
                    })
                }
            });
        }


    }
}();