// checkbox -------------------------------------------------------
let checkboxes = $('.checkbox').toArray();
let svgns = "http://www.w3.org/2000/svg", container = document.getElementById('cont');

// selects last selected checkbox
function f(index) {
    for (checkbox of checkboxes) {
        if (checkbox !== checkboxes[index - 1]) {
            checkbox.checked = false;
        }
    }
}

// return number of selected checkbox
function get$r() {
    for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) return i + 1;
    }
}

// validation -------------------------------------------------------
let input = $('#input');

$(input).bind('input load', function () {
    let sign;
    if (this.value.length > 0) {
        sign = this.value.charAt(0);
    }
    sign = (sign === '-') ? sign : "";
    this.value = sign + this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');
});

// graphic listener -------------------------------------------------------

let graphic = document.getElementById("graph");
let slider$entry = ice.ace.instance('j_idt18');

// click on graphic
$(graphic).click(function (event) {
    let handler = get$handler(event);

    if (get$r() !== undefined) {
        slider$entry.setValue(handler.x);
        slider$entry.input[0].value = handler.x;
        $('#input').val(handler.y);
        $('#submit').click();
        draw$point(handler.x, handler.y);
    } else {
        $('#submit-msg').html('Please select value of R')
    }
});

// submit and clear -------------------------------------------------------

$('#submit').click(function () {
    $('#submit-msg').html(submit$action());
})

$('#clear').click(function () {
    let pointers = $(".pointer");
    for (let i = 0; i < pointers.length; i++) {
        graphic.removeChild(pointers[i]);
    }
})

function submit$action() {
    let float$value = parseFloat($(input).val());

    if (!isNaN(float$value)) {
        if (float$value < -5.0) {
            return 'Value of the Y must be greater than -5';
        }
        if (float$value > 3.0) {
            return 'Value of the Y must be less than 3';
        }
    } else {
        return 'Please enter a valid Y';
    }

    if (get$r() === undefined) {
        return 'Please select value of R';
    }
    draw$point(slider$entry.getValue(), float$value);
    return '';
}

// handler x,y -------------------------------------------------------

// true coordinates
function get$handler(event) {
    let rect = graphic.getBoundingClientRect();

    let pixel$x = (event.clientX - rect.x) * 400 / rect.width;
    let pixel$y = (event.clientY - rect.y) * 400 / rect.height;

    let r = (get$r() === undefined) ? 2 : get$r();

    let x = (fix$number(pixel$x, 30, 370) - 200) * r / 160;
    let y = -(fix$number(pixel$y, 30, 370) - 200) * r / 160;

    return {
        x: fix$number(Math.round(x), -2, 2), //X
        y: fix$number(y, -5, 3)
    };
}

function fix$number(number, min, max) {
    if (number < min) return min;
    if (number > max) return max;
    return number;
}

// drawing -------------------------------------------------------

// draws point
function draw$point(x, y) {
    let circle = document.createElementNS(svgns, 'circle');
    let r = (get$r() === undefined) ? 2 : get$r();
    let relative$x = 160 * x / r + 200;
    let relative$y = -160 * y / r + 200;
    circle.setAttribute( 'cx', relative$x);
    circle.setAttribute( 'cy', relative$y);
    circle.setAttribute('r', 3);
    circle.setAttribute('data-x', x);
    circle.setAttribute('data-y', y);
    circle.classList.add("pointer");
    graphic.appendChild(circle);
}

// draw all points
function draw$points() {
    let data = Array();
    $("#j_idt34\\:points tr").each(function (i) {
        data[i] = Array();
        $(this).children('td').each(function (ii) {
            data[i][ii] = $(this).text();
        });
    })
    for (let i = 1; i < data.length; i++) {
        if (data[i][0])
            draw$point(data[i][0], data[i][1]);
    }
}

// animated changed R
$('.checkbox').click(function () {
    let r = (get$r() === undefined) ? 2 : get$r();
    let pointers = $(".pointer");
    let relative$x;
    let relative$y;
    let x;
    let y;
    for (let i = 0; i < pointers.length; i++) {
        x = pointers[i].dataset.x;
        y = pointers[i].dataset.y;
        relative$x = 160 * x / r + 200;
        relative$y = -160 * y / r + 200;

        $(pointers[i]).animate({
            cx: relative$x,
            cy: relative$y
        }, {duration: 500, queue: false});
    }
});

$(document).ready(function() {
    draw$points();
});

document.addEventListener("DOMContentLoaded", function(){
    draw$points();
});

