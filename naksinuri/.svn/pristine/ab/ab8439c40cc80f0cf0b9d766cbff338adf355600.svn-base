var CurrentFontSize = 10
var CurrentLineHeight = 10

function SetFontSize(SizeFlag) {
	obj = document.getElementById("fontSize")
	if (SizeFlag == 'B') {
			CurrentFontSize = CurrentFontSize + 1
			CurrentLineHeight = parseInt(CurrentFontSize*1.5)
			obj.style.fontSize = CurrentFontSize + "pt"
			obj.style.lineHeight = CurrentLineHeight + "pt"
	}else {
		if (CurrentFontSize>0) {
			CurrentFontSize = CurrentFontSize - 1
			CurrentLineHeight = parseInt(CurrentFontSize*1.5)
		}
		obj.style.fontSize = CurrentFontSize + "pt"
		obj.style.lineHeight = CurrentLineHeight + "pt"
	}
}

function SetSummarySize() {
	if (strlength == 'strshort') {
		document.getElementById("img_more").Style.display = 'none';
	}
}