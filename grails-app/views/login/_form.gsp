<div id="form" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Sign-In</h4>
            </div>
            <g:form class="form-horizontal">

                <div class="modal-body">

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="userName">UserName:</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="userName" name="userName" placeholder="User-Name"
                                   required>
                        </div>
                    </div>

                    <div class="form-group">

                        <label class="control-label col-sm-2" for="password">Password:</label>

                        <div class="col-sm-10 ">
                            <input type="password" class="form-control" id="password" name="password" placeholder="Password"
                                   required>
                        </div>
                    </div>

                </div>


                <div class="modal-footer">
                    <g:submitButton type="submit" class="btn btn-default" name="Login" />

                    <a href="https://accounts.google.com/o/oauth2/auth?redirect_uri=http%3A%2F%2Flocalhost:8080%2Flogin%2FgoogleAuth&response_type=code&client_id=697244225685-p96bulii3ubavebeuivihpdvibrc1o2a.apps.googleusercontent.com&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile&approval_prompt=force&access_type=offline"> SignInWithGoogle </a>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                </div>
            </g:form>
        </div>

    </div>
</div>
