let cdigits = $('.cdigit').toArray();
const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

// refresh clock
function refresh$time() {
    let date = new Date();

    let hours = date.getHours();
    let minutes = date.getMinutes();
    let seconds = date.getSeconds();

    cdigits[0].innerHTML = ((hours-hours%10)/10).toString();
    cdigits[1].innerHTML = (hours%10).toString();
    cdigits[2].innerHTML = ((minutes-minutes%10)/10).toString();
    cdigits[3].innerHTML = (minutes%10).toString();
    cdigits[4].innerHTML = ((seconds-seconds%10)/10).toString();
    cdigits[5].innerHTML = (seconds%10).toString();

    $('#day').html(date.getDate());
    $('#month').html(months[date.getMonth()]);
    $('#year').html(date.getFullYear());
}

// refresh$clock() every 12 second
$(function () {
    setInterval(refresh$time, 12000);
    refresh$time();
})
