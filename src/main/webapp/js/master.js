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

function findSmallestAsset(element) {
    if (element.assets.length > 0) {
        var width = 9999;
        var found = null
        for (i in element.assets) {
            var asset = element.assets[i];
            if (asset.type == 'image' && asset.typeData.width < width) {
                found = asset;
                width = asset.typeData.width;
            }
        }
        return found;
    }
}

function findFirstVideo(element) {
    if (element.assets.length > 0) {
        for (i in element.assets) {
            var asset = element.assets[i];
            if (asset.type == 'video') return asset;
        }
    }
}
$(function(){
    for (elementId in data) {
        if (data[elementId].type == "image") {
        $('.element').each(function(index, element){
            var el = $(element);
            if (el.data('media-id') === elementId && element.type == 'image') {
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
        if (data[elementId].type == "video") {
            var videoElement = data[elementId];
            var templ = $('#video-template').clone();
            templ.attr('id', null);
            templ.css('display', 'block');
            var img = findSmallestAsset(videoElement)
            templ.find('img').attr('src', img.file).attr('width', img.typeData.width).attr('height', img.typeData.height);
            s = '<ul>';
            for (i in videoElement.assets) {
                var asset = videoElement.assets[i];
                if (asset.type == 'image') {
                    s += '<li>' +
                        '<a href="#" class="imgswap" data-pic='+asset.file+'>' + asset.typeData.width + "x" + asset.typeData.height + '</a>' +
                        '</li>';
                }
            }
            s += '</ul>';
            templ.append(s);
            var vid = findFirstVideo(videoElement);
            var video = templ.find('video').attr('src', vid.file).attr('poster', vid.typeData.stillImageUrl);
            for (i in videoElement.assets) {
                var asset = videoElement.assets[i];
                if (asset.type == 'video') {
                    s = '<source ' +
                        'src="' + asset.file + '" ' +
                        'type="' + asset.mimeType + '" ' +
                        '>';
                    video.append(s);
                }
            }

            $('div.body').append(templ);
        }
    }
    $('.imgswap').click(function(evt) {
        console.log("clicked");
        console.log(evt.srcElement);
        imgswap(evt.srcElement);
        return false;
    })
})