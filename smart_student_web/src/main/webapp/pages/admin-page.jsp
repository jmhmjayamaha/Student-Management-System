<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("name") == null) {
		response.sendRedirect("admin-login.html");
	}
%>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.js"></script>
<script>
	var app = angular.module("myApp", [ "ngRoute" ]);
	app.config(function($routeProvider) {
		$routeProvider.when("/", {
			templateUrl : "admin-home.html"
		}).when("/addCourse", {
			templateUrl : "pages/course-form-submit.html"
		}).when("/courses", {
			templateUrl : "subject-view.html",
			controller : "subjectController"
		}).when("/addLecturer", {
			templateUrl : "pages/lecture-form-submit.html"
		}).when("/Lecturer", {
			templateUrl : "teachers-view.html",
			controller : "teacherController"
		}).when("/addStudent", {
			templateUrl : "student-save.html"
		}).when("/student", {
			templateUrl : "student-view.html",
			//controller : "studentController"
		});
	});
	app.controller('subjectController' , function($scope, $http ) {
		$http.get('http://localhost:8080/api/subject-list').
			then(function(response) {
				$scope.subjects = response.data;
			});
	});
	
	app.controller('teacherController', function($scope, $http) {
		$http.get('http://localhost:8080/api/teacher-list').then(function(response) {
			$scope.teachers = response.data;
		});
	});
	/* app.controller('studentController', function($scope, $http) {
		$scope.GetAllData = function() {
			$http
					.get(
							"http://localhost:8080/api/students"
									).then(function(response) {
						$scope.students = response.data;
					});
		}
	});  */

	app.controller('studentController', function($scope, $http) {
		//$scope.GetAllData = function() {
		$http.get("http://localhost:8080/api/students").then(
				function(response) {
					$scope.students = response.data;
				});
		//}
	});
</script>
<style type="text/css">
body {
	background-color: #cbe7f1;
}

#content {
	width: 1110px;
}

#header {
	background-color: #7aeab5;
	width: 1100px;
	padding: 10 10 10 10;
}

#menu {
	text-align: left;
	background-color: #4eadb3;
	width: 1100px;
	margin-top: 10px;
	font-size: 1.5em;
}

#menu li {
	display: inline;
}

#menu a:hover {
	color: white;
}
#body-content {
	text-align : left;
	padding-left : 20px;
	
}
</style>
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<div align="" id="main">
			<div id="content">
				<div id="header" class="img-rounded">
					<h1>Aministrator's page</h1>
					<h3>
						welcome
						<%
						out.println(session.getAttribute("name"));
					%>
					</h3>
					<a href="logout.jsp"><button class="btn">logout</button></a>
					&nbsp;&nbsp;&nbsp;
				</div>
				<div id="menu" class="img-rounded">
					<ol>
						<li><a href="#/">Home</a></li>
						<li><a href="#addCourse">Add Course</a></li>
						<li><a href="#courses">Courses</a></li>
						<li><a href="#addLecturer">Add Lecturer</a></li>
						<li><a href="#Lecturer">Lecturer</a></li>
						<li><a href="#addStudent">Add Student</a></li>
						<li><a href="#student">Student</a></li>
					</ol>

					
				</div>
				<div id="body-content">
					<div ng-view></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>