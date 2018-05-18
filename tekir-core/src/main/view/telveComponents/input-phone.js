(function () {
    var tekir = window.tekir = window.tekir || {};

    function handleKeyPress(event) {
        var val = event.target.value;
        var charCode = event.charCode;
        if (val.indexOf('+') < 0 && charCode === 43) { //+
            return true;
        }
        return (charCode > 47 && charCode < 58);
    }

    function handleKeyUp(event) {
        var val = event.target.value;
        if (!libphonenumber.isValidNumber(val)) {
            return false;
        }

        if (val.indexOf('+') > 0) { // remove inner '+' char
            val = val.split('+').join('');
        }
        if (val.length > 3 && val.indexOf('+') !== 0) {
            val = libphonenumber.formatNumber({country: 'TR', phone: val}, 'International');
        } else {
            val = libphonenumber.formatNumber(val, 'International');
        }
        event.target.value = val;
    }

    var phoneRegex = /^([+]?)+([\s0-9]*$)/g;

    function handlePaste(event) {

        try {
            var text = event.clipboardData.getData('text');
            return text.match(phoneRegex) !== null;
        } catch (ex) {
            //old browser
            var el = event.target;
            var oldValue = el.value;
            setTimeout(function () {
                if (!el.value.match(phoneRegex)) {
                    el.value = oldValue;
                }
            }, 80);
        }
        return true;
    }

    function handleChange(event) {
        var val = event.target.value;
        if (!val.match(phoneRegex)) {
            event.target.value = event.target._oldValue || "";
        } else {
            event.target._oldValue = event.target.value;
        }
    }

    tekir.attachPhoneInput = function (id) {
        var el = document.getElementById(id);
        if (!el) {
            console.warn('Element with id (' + id +
                ') not found. Phone input functions will not be work properly!');
            return;
        }

        el.onkeypress = handleKeyPress;
        el.onkeyup = handleKeyUp;
        el.onpaste = handlePaste;
        el.addEventListener('change', handleChange);
    };
}());