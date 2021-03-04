//---------------------------------------------------------
// グローバル変数
//---------------------------------------------------------
const ELEMENT_NAME_START_DATE           = "start_date";
const ELEMENT_NAME_START_TIME_HOUR      = "start_time_hour";
const ELEMENT_NAME_START_TIME_MINUTES   = "start_time_minutes";
const ELEMENT_NAME_END_DATE             = "end_date";
const ELEMENT_NAME_END_TIME_HOUR        = "end_time_hour";
const ELEMENT_NAME_END_TIME_MINUTES     = "end_time_minutes";

const todayObj = window.RNGD_TODO.getTodayDateTime();
const dtStr = `${todayObj.year}-${todayObj.month}-${todayObj.date}`;
const hourStr = todayObj.hour;
const minutesStr = todayObj.minutes;

const ELEMENT_NAME_SORT_ORDER_PULLDOWN  = "order";
const ELEMENT_NAME_FILTER_PULLDOWN      = "filter";

const ELEMENT_NAME_ORDER_FILTER_FORM    = "orderFilterForm";

const changePulldownTarget = [
    ELEMENT_NAME_SORT_ORDER_PULLDOWN,
    ELEMENT_NAME_FILTER_PULLDOWN
];
changePulldownTarget.forEach((elemName) => {
    $(document).on("change", `[name=${elemName}]`, (obj) => {
        const pulldownValue = obj.target.value;
        $(`[name=${elemName}]`).val(pulldownValue);
        $("#"+ELEMENT_NAME_ORDER_FILTER_FORM).submit();
    });
})


//---------------------------------------------------------
// 開始時刻、終了時刻の時分を0埋めする
//---------------------------------------------------------
const timeZeroPaddingTarget = [
    ELEMENT_NAME_START_TIME_HOUR, 
    ELEMENT_NAME_START_TIME_MINUTES, 
    ELEMENT_NAME_END_TIME_HOUR, 
    ELEMENT_NAME_END_TIME_MINUTES
];

timeZeroPaddingTarget.forEach((elemName) => {
    $(document).on("change", `[name=${elemName}]`, (obj) => {
        let targetValue = obj.target.value;
        targetValue = RNGD_TODO.setZeroPadding(targetValue, 2);
        obj.target.value = targetValue;
    });
});  


//---------------------------------------------------------
// グローバル関数
//---------------------------------------------------------
const setStartDate = function() {
    // 開始日の設定
    $(`[name=${ELEMENT_NAME_START_DATE}]`).val(dtStr);
    $(`[name=${ELEMENT_NAME_START_TIME_HOUR}]`).val(hourStr);
    $(`[name=${ELEMENT_NAME_START_TIME_MINUTES}]`).val(minutesStr);
}

