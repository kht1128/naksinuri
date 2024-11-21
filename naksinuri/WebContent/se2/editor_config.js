(function($){
    $(document).ready(function() {
        if(window.location.href.indexOf("localhost:8080/se2/SmartEditor2Skin.html") !== -1) {
            // 만약 현재 페이지가 스마트 에디터의 디폴트 페이지라면, 다른 페이지로 리다이렉트
            window.location.href = "/your-custom-page"; // 수정 필요
            return;
        }
        
        $(".smarteditor").each( function(index){
            var get_id = $(this).attr("id");

            if( !get_id || $(this).prop("nodeName") != 'TEXTAREA' ) return true;

            nhn.husky.EZCreator.createInIFrame({
                oAppRef: oEditors,
                elPlaceHolder: get_id,
                sSkinURI: editor_url+"/SmartEditor2Skin.html",	
                htParams : {
                    bUseToolbar : true,				
                    bUseVerticalResizer : true,		
                    bUseModeChanger : true,			
                    fOnBeforeUnload : function(){
                        //alert("완료!");
                    }
                }, 
                fOnAppLoad : function(){
                    //예제 코드
                    //oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
                },
                fCreator: "createSEditor2"
            });
        });
    });
})(jQuery);