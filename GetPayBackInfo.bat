@echo off
cd "C:\Program Files\cURL\bin"
echo Mobile:%1%
curl "https://in.bookmyshow.com/serv/doSecureTrans.bms" -H "Cookie: __cfduid=dd1e516150ab54928d1e8a2572ecccc2c1476721783; tvc_vid=91476721737930; ci_id=b2968392-b2fc-4b53-b224-t157d37981a3-94214d3a0749; __gads=ID=bb96adfc9e20aa0d:T=1476721794:S=ALNI_MbzVziGLn5PiAxDv_llLgHTm7vCog; _vis_opt_exp_96_exclude=1; _vis_opt_exp_92_exclude=1; _vis_opt_exp_95_exclude=1; km_ai=OUnfk8QiBiQhq0NiGMrHn"%%"2B0GuiE"%%"3D; km_lv=x; _vis_opt_exp_101_exclude=1; _vis_opt_exp_100_exclude=1; PHPSESSID=p5sdfl32ulde0fgq8sfsi23pg3; Rgn="%%"7CCode"%%"3DPUNE"%%"7Ctext"%%"3DPune"%%"7C; _vis_opt_s=6"%%"7C; OX_plg=swf|shk|pm; banner=2; preferredLanguages="%%"7CemailID"%%"3D"%%"7Clanguages"%%"3D"%%"7C; _gat=1; _dc_gtm_UA-27207583-8=1; _gat_UA-27207583-8=1; tvc_clientid=1; tvc_movies_sq=; lngTransId=1637120576; BOOKINGID=WSZSWT4; _vwo_uuid_v2=8D213FFD6B82AD32D3CCD09C64536328|34935c42fb788ae7f5136e8db0514568; _vis_opt_test_cookie=1; WZRK_P=https"%%"3A"%%"2F"%%"2Fin.bookmyshow.com"%%"2Fpayment"%%"2F"%%"3Fcid"%%"3DESCH"%%"26sid"%%"3D16315"%%"26ety"%%"3DMT"%%"26etk"%%"3DY"%%"26mtk"%%"3DY"%%"26ec"%%"3DET00041745; WZRK_G=1901fcc9506f44cf936a0cdcf80bfb19; WZRK_S_RK4-47R-98KZ="%%"7B"%%"22p"%%"22"%%"3A2"%%"2C"%%"22s"%%"22"%%"3A1481050224"%%"2C"%%"22t"%%"22"%%"3A1481050159"%%"7D; tvc_bmscookie=GA1.3.1197793010.1476721773; kvcd=1481050161323; km_vs=1; km_uq=; userCine="%%"7CPAYTAB"%%"3DRedeemPoints"%%"7COtpRebookLink"%%"3Dhttp"%%"253A"%%"252F"%%"252Fin.bookmyshow.com"%%"252Fbuytickets"%%"252Fkahaani-2-ua-pune"%%"252Fmovie-pune-ET00041745-MT"%%"252F20161207"%%"253Fcid"%%"253DESCH"%%"2526sid"%%"253D16315"%%"7CRPT"%%"3D1637120576"%%"7CRPC"%%"3D0"%%"7Cety"%%"3DMT"%%"7CSSID"%%"3D16315"%%"7CSVC"%%"3DESCH"%%"7CRP"%%"3DY"%%"7Cpop"%%"3DESCH"%%"3BESPN"%%"3BCPKT"%%"3BCNWE"%%"3BPVPN"%%"3BINPA"%%"3BPUNB"%%"3BESPM"%%"3BCPRP"%%"3BCPAR"%%"3BPVKP"%%"3BINPN"%%"3BCTMP"%%"3BCTSR"%%"3BCXPN"%%"3BADCW"%%"3BPUVB"%%"3BADGL"%%"3BFMFP"%%"3BFMJP"%%"7Cmrs"%%"3DESCH"%%"3B"%%"7C" -H "Origin: https://in.bookmyshow.com" -H "Accept-Encoding: gzip, deflate, br" -H "Accept-Language: en-US,en;q=0.8" -H "User-Agent: Mozilla/5.0 (iPad; CPU OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1" -H "Content-Type: application/x-www-form-urlencoded" -H "Accept: application/xml, text/xml, */*; q=0.01" -H "Cache-Control: no-store" -H "X-Requested-With: XMLHttpRequest" -H "Connection: keep-alive" -H "Referer: https://in.bookmyshow.com/payment/?cid=ESCH&sid=16315&ety=MT&etk=Y&mtk=Y&ec=ET00041745" --data "a=WEBTOUCH&v=ESCH&t=1637120576&c=GETIMINTS2SBALANCEPOINTS&p1="%%"7CTYPE"%%"3DIMINTS2S"%%"7CMOBILEORCARDNO"%%"3D%1"%%"7CPIN"%%"3D0000"%%"7C&p2=&p3=&p4=&p5=&p6=&p7=&p8=&p9=&p10=" --compressed
echo. 
exit