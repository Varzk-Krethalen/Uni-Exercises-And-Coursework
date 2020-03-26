window.addEventListener("load", function(){
	
	console.log(document.querySelectorAll("li").length);
	console.log(document.querySelectorAll("p").length);
	console.log(document.querySelectorAll(".highlight").length);
	console.log(document.querySelectorAll("ol > li").length);
	console.log(document.querySelectorAll("li.highlight").length);
	console.log(document.querySelectorAll("li, p.highlight").length);
	btn1();
	btn2();
	table();
});

function btn1(){
	
	document.getElementById("btn1").addEventListener("click", function(){
		
		console.log(document.getElementById("para").innerHTML);
		document.getElementById("para").innerHTML="Success";
	})
}

function btn2(){
	
	document.getElementById("btn2").addEventListener("click", function(){
		
		var a = (document.querySelectorAll("p"));
		for (var i = 0; i < a.length; i++)
		{
			console.log(a[i].innerHTML);
		}
	})
}

function table(){
	
	var a = (document.querySelectorAll("td"));
		for (var i = 0; i < a.length; i++)
		{
			a[i].addEventListener("click", tableReplace);
		}
}

function tableReplace(){
	
	this.innerHTML="Success";
}