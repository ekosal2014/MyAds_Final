// JavaScript Document
// Company : Webcash
// Author : PARK SEOK-HWAN
// Date : 2014/07/17
// Title : Mobile

// (2014/07/17) IE9 미만에서 HTML5 DOM 인식
document.createElement('header');
document.createElement('nav');
document.createElement('article');
document.createElement('section');
document.createElement('aside');
document.createElement('footer');

// (2014/07/17) Location Auto Close
window.addEventListener("load", function(){
	setTimeout(loaded, 100);
}, false);
function loaded(){
	window.scrollTo(0, 1);
}
