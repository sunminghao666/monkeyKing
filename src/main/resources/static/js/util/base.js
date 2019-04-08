var base = {
	domain : window.location.protocol + "//" + window.location.host
			+ "/",
	newurl : window.location.protocol + "//" + window.location.host + "/",
	keyStr : "t171420100302rsa",
	ivStr : "t171420100302rsa"
};
document.write("<script type='text/javascript' src='" + base.domain
		+ "js/util/aes.js' ><\/script>");
document.write("<script type='text/javascript' src='" + base.domain
		+ "js/util/pad-iso10126-min.js' ><\/script>");
function URLencodeForBase64(a) {
	return escape(a).replace(/\+/g, "%2B").replace(/\"/g, "%22").replace(/\'/g,
			"%27").replace(/\//g, "%2F")
}
function aesEncrypt(f) {
	var b = CryptoJS.enc.Utf8.parse(f);
	var d = CryptoJS.enc.Utf8.parse(base.keyStr);
	var c = CryptoJS.enc.Utf8.parse(base.ivStr);
	var e = CryptoJS.AES.encrypt(b, d, {
		iv : c,
		mode : CryptoJS.mode.CBC
	});
	var a = CryptoJS.enc.Base64.stringify(e.ciphertext);
	a = URLencodeForBase64(a);
	return a
};
