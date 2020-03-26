window.addEventListener("load", prepareGallery);

function prepareGallery() {
	var i, links = document.querySelectorAll("#images a");
	paragraph();
	for(i = 0; i < links.length; i++){
		links[i].addEventListener("click", function(evt) {
			showPic(this);
			evt.preventDefault();
		});
	}
}

function showPic(whichPic) {
	var source = whichPic.getAttribute("href");
	var placeholder = document.getElementById("placeholder");
	var title = whichPic.getAttribute("title");
	var desc = document.querySelector("#description");
	desc.textContent = title;
	placeholder.setAttribute("src", source);
	placeholder.setAttribute("alt", title);
}

function paragraph(){
	var para = document.querySelector(".long");
	var show = document.querySelector("#show");
	var hide = document.querySelector("#hide");
	show.addEventListener("click", function() {para.style.display = 'inline';});
	hide.addEventListener("click", function() {para.style.display = 'none';});
}

