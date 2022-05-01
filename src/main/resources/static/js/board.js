let index = {
	init: function(){
		$("#btn-save").on("click",()=>{
			this.save();
		});
		$("#btn-delete").on("click",()=>{
			this.deleteById();
		});
		$("#btn-update").on("click",()=>{
			this.update();
		});
		$("#btn-reply-save").on("click",()=>{
			this.replySave();
		});
	},
	
	save : function(){
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), // json 문자열 변경 // http body데이터
			contentType : "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (mime)
			dataType: "json"
		}).done(function(resp){
			console.log(resp)
			if(resp.data == "1"){
				alert("글 작성이 완료되었습니다.")
			}
			else{
				alert("글 작성에 실패했습니다.");
				alert(resp.data);
			}
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error))
		});
	},
	 
	deleteById : function(){
		let el = document.querySelector("#board-number");
		id = el.dataset.id;
		console.log(id)
		console.log(typeof id)
		$.ajax({
			type: "DELETE",
			url: "/api/board/"+id,
			dataType: "json"
		}).done(function(resp){
			alert("삭제가 완료되었습니다");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error))
		});
	},
	
	update : function(){
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data), // json 문자열 변경 // http body데이터
			contentType : "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (mime)
			dataType: "json"
		}).done(function(resp){
			alert("글수정이 완료되었습니다");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error))
		});
	},
	
	replySave : function(){
		let data = {
			boardId: $("#boardId").val(),
			content: $("#reply-content").val()
		}
		console.log(data);
		console.log("click")
		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data), // json 문자열 변경 // http body데이터
			contentType : "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (mime)
			dataType: "json"
		}).done(function(resp){
			console.log(resp)
			if(resp.data == "1"){
				alert("댓글 작성이 완료되었습니다.")
			}
			else{
				alert("댓글 작성에 실패했습니다.");
				alert(resp.data);
			}
			location.href = `/board/${data.boardId}`;
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	}
}

index.init();