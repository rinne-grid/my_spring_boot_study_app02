if(!window.hasOwnProperty("RNGD_TODO")) {
    window.RNGD_TODO = {};
}

/***
 * @returns {Object}
 */
window.RNGD_TODO.getTodayDateTime = function() {
    const date = new Date();
    const yearYYYY  = date.getFullYear() + "";
    const monthMM   = ("0" + (date.getMonth()+1)).slice(-2);
    const dateDD    = ("0" + date.getDate()).slice(-2);
    const hoursHH   = ("0" + date.getHours()).slice(-2);
    const minutesMI = ("0" + date.getMinutes()).slice(-2);
    const secondSS  = ("0" + date.getSeconds()).slice(-2);
    return {
        "year"      : yearYYYY,
        "month"     : monthMM,
        "date"      : dateDD,
        "hour"     : hoursHH,
        "minutes"   : minutesMI,
        "second"    : secondSS
    }
};

window.RNGD_TODO.setZeroPadding = function(targetStr, digits) {
    let padding = "0";
    const paddingCnt = digits - padding.length + 1;
    for(let i = 0; i < paddingCnt; i++) {
        padding += "0";
    }
    targetStr = (padding + targetStr).slice(-digits);
    return targetStr;
};
