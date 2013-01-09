function imgswap(linkElem) {
    var el = $(linkElem);
    console.log("Link Elem");
    console.log(el);
    var img = $(linkElem).parents('figure').find('img');
    console.log("Image");
    console.log(img);
    var imgsrc = $(linkElem).data('pic');
    console.log("Setting img "+img+" src to "+imgsrc);
    img.attr('src', imgsrc);
    img.attr('width', null);
    img.attr('height', null);
}
$(function(){
    for (elementId in data) {
        $('.element').each(function(index, element){
            var el = $(element);
            if (el.data('media-id') === elementId) {
                s = '<ul>';
                for (i in data[elementId].assets) {
                    var asset = data[elementId].assets[i];
                    s += '<li>' +
                        '<a href="#" class="imgswap" data-pic='+asset.file+'>' + asset.typeData.width + "x" + asset.typeData.height + '</a>' +
                        '</li>';
                }
                s += '</ul>';
                el.append(s);
            }
        });
    }
    $('.imgswap').click(function(evt) {
        console.log("clicked");
        console.log(evt.srcElement);
        imgswap(evt.srcElement);
        return false;
    })
})