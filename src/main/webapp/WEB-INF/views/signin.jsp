<%@page contentType="text/html; charset=UTF-8"%>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</head>

<body>
    <jsp:include page="layout/Header.jsp"></jsp:include>
    <div class="container" style="padding-top:100px;">
        <div class="row mt-5">
            <div class="col-12">
                <form method="POST" action="/signin">
                    <div class="form-group">
                        <label for="userid">ID</label>
                        <input type="text" class="form-control" id="userid" 
                        placeholder="아이디를 입력해주세요." name="id"/>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" 
                        placeholder="비밀번호를 입력해주세요." name="password"/>
                    </div>

                    <div class="form-group">
                        <label for="email">LOL ID</label>
                        <input type="text" class="form-control" id="email" 
                        placeholder="LOL 아이디을 입력해주세요." name="email"/>
                    </div>
                    <div class="form-group">
                        <label for="username">E-mail</label>
                        <input type="text" class="form-control" id="lolId" 
                        placeholder="이메일을 입력해주세요." name="lolid"/>
                    </div>
                    
                    <button type="submit" class="btn btn-primary">회원가입</button>
                </form>
            </div>
        </div>
        
    </div>
</body>

</html>