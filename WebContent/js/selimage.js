function selimage() {
	window
			.open(
					"upload/preUpload.action",
					"",
					"toolbar=no,location=no,directories=no,status=no,menubar=no,resizable=yes,copyhistory=no,scrollbars=yes,width=400,height=240,top="
							+ (screen.availHeight - 240)
							/ 2
							+ ",left="
							+ (screen.availWidth - 400) / 2 + "");
}

function savefile() {
	window
			.open(
					"upload/preFiles.action",
					"",
					"toolbar=no,location=no,directories=no,status=no,menubar=no,resizable=yes,copyhistory=no,scrollbars=yes,width=400,height=240,top="
							+ (screen.availHeight - 240)
							/ 2
							+ ",left="
							+ (screen.availWidth - 400) / 2 + "");
}
