window.addEventListener("load", init);

function init() 
{
	var a = document.getElementById("q8");
	a.addEventListener("click", click1);
	var b = document.getElementById("p1");
	b.addEventListener("click", click2);
	var c = document.querySelector("#rollpic");
	c.addEventListener("mouseover", rollover);
	c.addEventListener("mouseout", rollout);
	var d = document.querySelector("#ladd");
	d.addEventListener("click", addItem);
	var e = document.querySelector("#btn");
	e.addEventListener("click", generate);
	var f = document.querySelector("#listadd");
	f.addEventListener("click", listGen);

}

function click1()
{
	event.preventDefault();
	console.log(this.getAttribute("href"));
	return;
}

function click2()
{
	var temp = this.getAttribute("title");
	this.setAttribute("title", this.innerHTML);
	this.innerHTML = temp;
	return;
}

function rollover()
{
	this.setAttribute("src", "MetroMap.png");
}

function rollout()
{
	this.setAttribute("src", "BusinessGraph.png");
}

function addItem()
{
	var list = document.querySelector("#list");
	var txt = document.querySelector("#litem").value;
	var li = document.createElement("li");
	var txtnode = document.createTextNode(txt);
	li.appendChild(txtnode);
	list.appendChild(li);
}

function generate() 
{
	var i, arr = (document.querySelector("#tableitem").value).split(",");
	var dst = document.querySelector("#dst");
	for(i = 0; i < arr.length; i++)
	{
		dst.appendChild(generateRow(i, arr[i]));
	}
}
function generateRow(idx, val) {
	var tr = document.createElement("tr");
	var td_idx = document.createElement("td");
	var td_val = document.createElement("td");
	td_idx.appendChild(document.createTextNode(idx));
	td_val.appendChild(document.createTextNode(val));
	tr.appendChild(td_idx);
	tr.appendChild(td_val);
	return tr;
}

function listGen()
{
	var item = (document.querySelector("#listness").value).split(",");
	var list = document.createElement("ul");
	var divdiv = document.getElementById("divdiv");
	divdiv.appendChild(list);
	var listitem;
	for(i = 0; i < item.length; i++)
	{
		listitem = document.createElement("li");
		listitem.appendChild(document.createTextNode(item[i]));
		list.appendChild(listitem);
	}
}