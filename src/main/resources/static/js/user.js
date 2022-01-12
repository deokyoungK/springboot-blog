let index = {
	init: function() {
		$("#btn-save").on("click", () => { //function(){},()=>{} this를 바인딩하기 위해서!
			this.save();
		});
		$("#btn-update").on("click", () => { //function(){},()=>{} this를 바인딩하기 위해서!
			this.update();
		});
	},

	save: function() {

		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		//ajax호출시 default가 비동기호출
		//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청!
		//ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바오브젝트로 변환해줌 
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), //자바스크립트를 JSON으로 변환
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
			dataType: "json"
		}).done(function(resp) {
			if(resp.status===500){
				alert("회원가입에 실패하였습니다.");
			}else{
				alert("회원가입이 완료되었습니다.");
				location.href = "/";
			}
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	update: function() {
	
			let data = {
				id: $("#id").val(),
				username: $("#username").val(),
				password: $("#password").val(),
				email: $("#email").val()
			};
			//ajax호출시 default가 비동기호출
			//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청!
			//ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바오브젝트로 변환해줌 
			$.ajax({
				type: "PUT",
				url: "/user",
				data: JSON.stringify(data), //자바스크립트를 JSON으로 변환
				contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
				dataType: "json"
			}).done(function(resp) {
				alert("회원 수정이 완료되었습니다.");
				location.href = "/";
			}).fail(function(error) {
				alert(JSON.stringify(error));
			});
		}

}
index.init();
