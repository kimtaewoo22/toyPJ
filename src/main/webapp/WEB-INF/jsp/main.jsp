<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOL 전적조회</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript">
function search(){
	var gameName = $("#gameName").val();
	
	if(gameName == null || gameName == ""){
		alert("소환사 이름을 적어주세요");
		return;
	};
	
	$.ajax({
		url		:	"${pageContext.request.contextPath}/leagueInfo",
		method	:	"post",
		data	:	{gameName : gameName},
		success :	function(jsonData){
			console.log(jsonData[0]);
			if(jsonData.length > 0){
				for(i=0; i<jsonData.length; i++){
					var data = jsonData[i];
					if(data.queueType == "RANKED_SOLO_5x5"){
						var tier = data.tier;
						var rank = data.rank;
						var gameName = data.summonerName;
						var leaguePoints = data.leaguePoints;
						var win = data.wins;
						var losse = data.losses;
						
						$("#name").text(gameName);
						$("#tier").text(tier+rank);
						$("#leaguePoints").text(leaguePoints);
						$("#win").text(win);
						$("#losse").text(losse);
					}
				};
				$("#result").show();
				$("#result2").hide();
			}else{
				$("#result").hide();
				$("#result2").show();
			}
		}
	});
};
</script>
</head>
<body>
	<h1>LOL 전적조회</h1>
	<div>
		<input id="gameName" type="text"/>
		<input type="button" onclick="javascript:search();" value="조회"/>
	</div>
	<table id="result" style="display:none;">
		<tr>
			<th>소환사 이름</th>
			<td id="name"></td>
		</tr>
		<tr>
			<th>티어</th>
			<td id="tier"></td>
		</tr>
		<tr>
			<th>포인트</th>
			<td id="leaguePoints"></td>
		</tr>
		<tr>
			<th>승</th>
			<td id="win"></td>
		</tr>
		<tr>
			<th>패</th>
			<td id="losse"></td>
		</tr>
	</table>
	<div id="result2" style="display:none;">
		언랭 입니다.
	</div>
</body>
</html>