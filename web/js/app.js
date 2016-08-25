
$("#J_uploadbtn").click(function(e) {
    do_upload();
});


function do_upload()
{
    var $form = $('#pf_form');

    var strUrl = G_VAR_CONTEXT_PATH + '/getupload';

    fileUpload($form, strUrl, null);

}


function fileUpload(form, action_url, div_id) {
    
    // Create the iframe...
    var iframe = document.createElement("iframe");
        iframe.setAttribute("id", "upload_iframe");
        iframe.setAttribute("name", "upload_iframe");
        iframe.setAttribute("style", "border: none; display: none;");
    
    // Create the div...
    var div = document.createElement("div");        
        div.setAttribute("style", "border: none; display: none;");
        //div.append(iframe);
        div.appendChild(iframe);

    // Add to document...
    form.append(div);
    window.frames['upload_iframe'].name = "upload_iframe";

    iframeId = document.getElementById("upload_iframe");

    // Add event...
    var eventHandler = function() {

        if (iframeId.detachEvent)
            iframeId.detachEvent("onload", eventHandler);
        else
            iframeId.removeEventListener("load", eventHandler, false);

        // Message from server...
        if (iframeId.contentDocument) {
            content = iframeId.contentDocument.body.innerHTML;
        } else if (iframeId.contentWindow) {
            content = iframeId.contentWindow.document.body.innerHTML;
        } else if (iframeId.document) {
            content = iframeId.document.body.innerHTML;
        }

        alert(content);
    };

    if (iframeId.addEventListener)
        iframeId.addEventListener("load", eventHandler, true);
    if (iframeId.attachEvent)
        iframeId.attachEvent("onload", eventHandler);

    // Set properties of form...
    form.attr("target",  "upload_iframe");
    form.attr("action",  action_url);
    form.attr("method",  "post");
    form.attr("enctype", "multipart/form-data");
    form.attr("encoding","multipart/form-data");

    // Submit the form...
    form.submit();

}

