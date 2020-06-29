var E = window.wangEditor
    var editor = new E('#editor')
    editor.customConfig.zIndex = 1
    // 自定义配置颜色（字体颜色、背景色）
    // 关闭粘贴样式的过滤
    editor.customConfig.pasteFilterStyle = false
    // 忽略粘贴内容中的图片
    editor.customConfig.pasteIgnoreImg = true
    // 自定义处理粘贴的文本内容
    editor.customConfig.pasteTextHandle = function (content) {
        // content 即粘贴过来的内容（html 或 纯文本），可进行自定义处理然后返回
        return content
    }
    //编辑菜单
    editor.customConfig.menus = [
        'head',
        'bold',
        'italic',
        'underline'
    ]
    //debug模式
    editor.customConfig.debug = location.href.indexOf('wangeditor_debug_mode=1') > 0


    editor.create()