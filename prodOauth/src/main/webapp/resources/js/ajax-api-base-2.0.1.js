//-----------------------------------------------------------------------------
var _____def_____;	/* ver: 2014.08.15 */
//-----------------------------------------------------------------------------
var x_apiUrl = "https://www.ksign.co.kr/base/api";
var x_apiKey = "undefined";

function setApiUrl(apiUrl) {
    x_apiUrl = apiUrl;
}

function setApiKey(apiKey) {
    x_apiKey = apiKey;
}

$.support.cors = true;
$.ajaxSetup ({
    // Disable caching of AJAX responses
    cache: false
});

var _ua = navigator.userAgent;
var _browser_ver = parseInt($.browser.version, 10);
//var _browser_ver = 10;
/*var browser = (function() {
    var s = navigator.userAgent.toLowerCase();
    var match = /(webkit)[ \/](\w.]+)/.exec(s) ||
                /(opera)(?:.*version)?[ \/](\w.]+)/.exec(s) ||
                /(msie) ([\w.]+)/.exec(s) ||
               !/compatible/.test(s) &amp;&amp; /(mozilla)(?:.*? rv:([\w.]+))?/.exec(s) ||
               [];
    return { name: match[1] || "", version: match[2] || "0" };
}());*/

var _is_ie = $.browser.msie;
var _is_ie_789 = $.browser.msie && (_browser_ver >= 7 && _browser_ver <= 9);
var _is_ie_10 = $.browser.msie && _browser_ver >= 10;
var _is_ie_11 = !!_ua.match(/Trident.*rv\:11\./);

var _iphone = _ua.indexOf("iPhone") >= 0;
var _ipad = _ua.indexOf("iPad") >= 0;
var _ios = _iphone || _ipad;
var _iosVersion = _ios && _ua.indexOf("OS ") >= 0 ? _ua.substr(_ua.indexOf("OS ")+3, 1) : "";

var _android = _ua.indexOf("Android") >= 0;

var _safari = _ua.indexOf("Safari") >= 0 && _ua.indexOf("Chrome") < 0;

//-----------------------------------------------------------------------------
var _____api_____;	/* ver: 2014.08.15 */
//-----------------------------------------------------------------------------

function handlePost(subUri, paramObj, callback_success, callback_error) {
    $.ajax({
    	type: "POST",
    	url: x_apiUrl + subUri + (_is_ie_789 ? "?callback=?&" : "?") + "apiKey=" + x_apiKey,
    	data: (_is_ie_789 ? encodeURIComponent(JSON.stringify(paramObj)) : JSON.stringify(paramObj)),
        contentType:"application/json; charset=utf-8",
    	dataType: (_is_ie_789 ? "jsonp" : "json"),
        crossDomain: true,
        xhrFields: {
            withCredentials: true
        },
		success: function(result, status, xhr) {
		     if(_is_ie_789 && result && result.code && result.message && result.code != 200 && result.code != 205) {
	             var json = result;
	             alert("[" + json.code + "] " + json.message);
	             if(typeof(callback_error) == 'function') callback_error(json);
	             return;
	         }
	         callback_success(result);			
		},
		
		error: function(xhr, status, error) {
			if(xhr.status >= 200 && xhr.status < 400) { 
	            var json = $.parseJSON(xhr.responseText);
	            alert("[" + json.code + "] " + json.message);
			}
			
            if(typeof(callback_error) == 'function') callback_error(json);            
		}
	});	
}

function handlePatch(subUri, paramObj, callback_success, callback_error) {
    $.ajax({
    	type: "PATCH",
    	url: x_apiUrl + subUri + (_is_ie_789 ? "?callback=?&" : "?") + "apiKey=" + x_apiKey,
    	data: (_is_ie_789 ? encodeURIComponent(JSON.stringify(paramObj)) : JSON.stringify(paramObj)),
        contentType:"application/json; charset=utf-8",
    	dataType: (_is_ie_789 ? "jsonp" : "json"),
        crossDomain: true,
        xhrFields: {
            withCredentials: true
        },        
        success: function(result, status, xhr) {
		     if(_is_ie_789 && result && result.code && result.message && result.code != 200 && result.code != 205) {
	             var json = result;
	             alert("[" + json.code + "] " + json.message);
	             if(typeof(callback_error) == 'function') callback_error(json);
	             return;
	         }
	         
	         callback_success(result);			
		},
		
		error: function(xhr, status, error) {
			var json = $.parseJSON(xhr.responseText);
			
			//if(xhr.status >= 200 && xhr.status < 400) { 
				alert("[" + json.code + "] " + json.message);
			//}
			
           if(typeof(callback_error) == 'function') callback_error(json);            
		}
	});	
}

function handleDelete(subUri, paramObj, callback_success, callback_error) {
    $.ajax({
    	type: "DELETE",
    	url: x_apiUrl + subUri + (_is_ie_789 ? "?callback=?&" : "?") + "apiKey=" + x_apiKey,
    	data: paramObj,
    	dataType: (_is_ie_789 ? "jsonp" : "json"),
        crossDomain: true,
        xhrFields: {
            withCredentials: true
        },        
        success: function(result, status, xhr) {
	         if(_is_ie_789 && result && result.code && result.message && result.code != 200) {
	             var json = result;
	             alert("[" + json.code + "] " + json.message);
	          
	             if(typeof(callback_error) == 'function') callback_error(json);
	             return;
	         }
	         
	         callback_success(result);			
		},
		error: function(xhr, status, error) {
			if(xhr.status >= 200 && xhr.status < 300) { 
	            var json = $.parseJSON(xhr.responseText);
	            alert("[" + json.code + "] " + json.message);
			}
           if(typeof(callback_error) == 'function') callback_error(json);           
		}
	});	
}

function handleGet(subUri, paramObj, callback_success, callback_error) {
    $.ajax({
    	type: "GET",
    	url: x_apiUrl + subUri,
    	data: paramObj,
        dataType: (_is_ie_789 ? "jsonp" : "json"),
        crossDomain: true,
        xhrFields: {
            withCredentials: true
        },        
        
		success: function(result, status, xhr) {
			 if(result && result.code && result.message && result.code == 404) {
				 //404추가 -- 20180321 손연경 
				 var json = result;
				 if(typeof(callback_error) == 'function') callback_success(json);
	             return;
			 } else if(result && result.code && result.message && result.code != 200) {
	             var json = result;
	             alert("[" + json.code + "] " + json.message);
	          
	             if(typeof(callback_error) == 'function') callback_error(json);
	             return;
	         } 
	         
	         callback_success(result);			
		},
		error: function(xhr, status, error) {
			//if(xhr.status >= 200 && xhr.status < 300) { 
	            var json = $.parseJSON(xhr.responseText);
	            alert("[" + json.code + "] " + json.message);
			//}
            if(typeof(callback_error) == 'function') callback_error(json);           
		}
	});	
}

//-----------------------------------------------------------------------------
var _____cookie_____;	/* ver: 2014.08.26 */
//-----------------------------------------------------------------------------

// from w3schools.com 
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

// domain 은 도메인쿠키 사용시, 2차 도메인까지로 설정함 
function setCookie(cname, cvalue, exdays, domain, path) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toGMTString() + "; ";
    var cookie = cname + "=" + cvalue + "; ";
    cookie += expires;
    if(domain && domain.length > 0) cookie += ("domain=" + domain + "; ");
    if(path && path.length > 0) cookie += ("path=" + path + "; ");
    
    document.cookie = cookie;
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
    }
    return "";
}

//-----------------------------------------------------------------------------
var _____localStorage_____;	
//-----------------------------------------------------------------------------

// from w3schools.com 
function setItem(name, value) {
	if(typeof(Storage) !== "undefined") {
		localStorage.setItem(name, value);
	}
}

function getItem(name) {
	if(typeof(Storage) !== "undefined") {
		var v = localStorage.getItem(name);
		if(v) return v;
	}
	
	return "";
}

//-----------------------------------------------------------------------------
var _____util_____;	
//-----------------------------------------------------------------------------
function _itoa(i, len) {
	var s = i + "";
	while(s.length < len) s = "0" + s;
	if(s.length > len) s = s.substring(0, len);
	return s;
}

function _ftoa(f, bdp_len, zeroCropped) {
	var bdp_filter = Math.pow(10, bdp_len);
	var s = Math.round(f * bdp_filter) / bdp_filter;
	if(s % 1 == 0) s = s + ".0";
	else s = s + "";
	
	var dpIdx = s.indexOf(".");
	var s_adp = s.substring(0, dpIdx);
	var s_bdp = s.substring(dpIdx + 1);
	while(s_bdp.length < bdp_len) s_bdp = s_bdp + "0";
	
	if(zeroCropped && s_adp == "0") return "." + s_bdp;
	return s_adp + "." + s_bdp;
}

function _timeToFormat(t) {
	return _itoa(Math.floor(t / 3600), 2) + ":" + 
	       _itoa(Math.floor((t % 3600) / 60), 2) + ":" + 
	       _itoa(t % 60, 2);
}

function _fileSizeToFormat(size) {
	if(size<1024) return size + ' bytes';
	else if(size<1024*1024) return (size/1024.0).toFixed(0) + ' KB';
	else if(size<1024*1024*1024) return (size/1024.0/1024.0).toFixed(1) + ' MB';
	return (size/1024.0/1024.0/1024.0).toFixed(1) + ' GB';
}

function _highlight(s, t, color) {
	var dIdx = s.indexOf(t);
	if(dIdx >= 0) {
		s = s.substring(0, dIdx) +
		    "<strong style='color: " + color + ";'>" + s.substring(dIdx, dIdx+t.length) + "</strong>" +
		    s.substring(dIdx+t.length);
	}
	return s;
}

function _omit(s, len) {
	if(len > 2 && s.length > len) s = s.substring(0, len-2) + "...";
	return s;
}

function _hasValueInCSV(s, t) {
	var split = s.split(",");
	for(var i = 0; i < split.length; i++) {
		if(split[i].trim() == t) return (i+1);
	}
	return 0;
}

function _emailValidation(s){
	if(!s.match(/^[a-zA-Z0-9_.-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9-]+$/))
		return false;
	return true;
}

function _mobileValidation(s){
	if(!s.match(/^[0][\d]{9,10}$/))
		return false;
	return true;
}