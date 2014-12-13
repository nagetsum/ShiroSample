<%--
    Document   : index
    Created on : 2014/12/03, 21:18:03
    Author     : agetsuma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Apache Shiro Sample</title>
        <link rel="stylesheet" href="/ShiroSample/css/bootstrap.min.css" media="screen" />
        <link ref="stylesheet" href="/ShiroSample/css/bootstrapValidator.min.css" media="screen" />
    </head>
    <body>
        <!--
          thanks for Bootsnipp
          http://bootsnipp.com/snippets/featured/parallel-signin-and-signup
        -->
        <div class="container">
            <div class="page-header">
                <h1>Apache Shiro <span class="text-info">Authentication</span></h1>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <form action="/ShiroSample/users/sign_up" id="signupForm" class="form-horizontal" method="post">
                        <h4>Sign Up</h4>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="inputEmail">Email</label>
                            <div class="col-md-8">
                                <input type="email" id="inputEmail" class="form-control" name="email" placeholder="e.g. yourname@gmail.com" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="inputPassword">Password</label>
                            <div class="col-md-8">
                                <input type="password" id="inputPassword" class="form-control" name="password" placeholder="Min. 8 Characters">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="confirmPassword">Confirm Password</label>
                            <div class="col-md-8">
                                <input type="password" id="confirmPassword" class="form-control" name="confirmPassword" placeholder="retype password for confirm">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="firstName">First Name</label>
                            <div class="col-md-8">
                                <input type="text" id="firstName" class="form-control" name="firstName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="lastName">Last Name</label>
                            <div class="col-md-8">
                                <input type="text" id="lastName" class="form-control" name="lastName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Role</label>
                            <div class="col-md-8">
                                <label class="radio-inline">
                                    <input type="radio" name="role"  value="STAFF" checked> staff
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="role"  value="MANAGER"> manager
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="checkbox">
                                    <label class="checkbox">
                                        <input type="checkbox" id="confirmTerm" name="confirmTerm" value="true">
                                        I agree all your Terms of Services
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <button class="btn btn-primary" type="submit">SignUp</button>
                                <a class="btn btn-default" data-toggle="modal" href="#signUpHelpModal">Help</a>
                            </div>
                        </div>
                    </form>
                </div><!-- col-md-6 -->
                <div class="col-md-6">
                    <shiro:guest>
                        <form action="/ShiroSample/users/sign_in" id="signinForm" class="form-horizontal" method="post">
                            <h4>Sign In</h4>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="signinEmail">Email</label>
                                <div class="col-md-8">
                                    <input type="email" id="signinEmail" class="form-control" name="email" placeholder="e.g. yourname@gmail.com" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="signinPassword">Password</label>
                                <div class="col-md-8">
                                    <input type="password" id="signinPassword" name="password" class="form-control" placeholder="Min. 8 Characters" >
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-9">
                                    <div class="checkbox">
                                        <label class="checkbox">
                                            <input type="checkbox" name="keepsigned" value="true">Keep me signed in
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-9">
                                    <button class="btn btn-primary" type="submit">SignIn</button>
                                </div>
                            </div>
                        </form>
                    </shiro:guest>
                    <shiro:notAuthenticated>
                        <shiro:user>
                            <h3>Welcome back <shiro:principal/> !</h3>
                            <form action="/ShiroSample/users/logout" method="post">
                                <p>Not <shiro:principal/> ?</p>
                                <p>Please signin <button type="submit" class="btn btn-link">here</button></p>
                            </form>
                        </shiro:user>
                    </shiro:notAuthenticated>
                    <shiro:authenticated>
                        <h3>Hello, <shiro:principal/></h3>
                        <form action="/ShiroSample/users/logout" method="post">
                            <div class="form-group">
                                <button type="submit" id="logout-button" class="btn btn-primary">logout</button>
                            </div>
                        </form>
                    </shiro:authenticated>
                </div><!-- col-md-6 -->
            </div><!-- .row -->

            <div class="page-header">
                <h1>Apache Shiro <span class="text-info">Autholization</span></h1>
            </div>
            <shiro:notAuthenticated>
                <h4>Please signin mamager role user.</h4>
            </shiro:notAuthenticated>
            <shiro:authenticated>
                <shiro:lacksRole name="MANAGER">
                    <h4>STAFF role can't refer user account list.</h4>
                </shiro:lacksRole>
                <shiro:hasRole name="MANAGER">
                    <h3>list users</h3>
                    <table class="table table-hover">
                        <thead>
                        <th>#</th>
                        <th>Email</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Role</th>
                        </thead>
                        <tbody>
                            <c:forEach var="account" items="${model}">
                                <tr>
                                    <td>${account.id}</td>
                                    <td>${account.email}</td>
                                    <td>${account.firstName}</td>
                                    <td>${account.lastName}</td>
                                    <td>${account.userRole}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </shiro:hasRole>
            </shiro:authenticated>

        </div><!-- .container -->

        <!-- modal -->
        <div class="modal fade" id="signUpHelpModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title">SignUp Help</h4>
                    </div>
                    <div class="modal-body">
                        <h4>Password Policy</h4>
                        <ul>
                            <li>Min 8 charactors only</li>
                        </ul>
                        <h4>What is Role?</h4>
                        <ul>
                            <li>staff: can singin only. can't refer user list which already signup.</li>
                            <li>manager: can refere user list.</li>
                        </ul>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">close</button>
                    </div>
                </div>
            </div>
        </div><!-- modal -->

        <script src="/ShiroSample/js/jquery-1.11.1.min.js"></script>
        <script src="/ShiroSample/js/bootstrap.min.js"></script>
        <script src="/ShiroSample/js/bootstrapValidator/bootstrapValidator.min.js"></script>
        <script src="/ShiroSample/js/shirosample.js"></script>
    </body>
</html>
