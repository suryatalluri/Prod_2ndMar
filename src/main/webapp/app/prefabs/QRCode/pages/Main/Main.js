Application.$controller('QRCodeController', ['$scope', '$element', function($s, $el) {
    'use strict';

    var _renderQRCode;

    function renderQRCode() {
        var qrele = $el.find('[name="qrcode"]')[0];
        var QRErrorCorrectLevel = {
            L: 1,
            M: 0,
            Q: 3,
            H: 2
        };
        qrele.innerHTML = '';
        var qrcode = new QRCode(qrele, {
            'width'        : $s.width,
            'height'       : $s.height,
            'typeNumber'   : $s.type,
            'colorDark'    : $s.darkcolor,
            'colorLight'   : $s.lightcolor,
            'correctLevel' : QRErrorCorrectLevel[$s.level]
        });
        qrcode.makeCode($s.text);
    }

    _renderQRCode = _.debounce(renderQRCode, 100);

    function propertyChangeHandler() {
        _renderQRCode();
    }

    $s.propertyManager.add($s.propertyManager.ACTIONS.CHANGE, propertyChangeHandler);

    $s.onInitPrefab = _renderQRCode;

}]);