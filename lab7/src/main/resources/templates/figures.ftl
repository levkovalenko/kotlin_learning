<!DOCTYPE html>
<html lang="ru">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Лаборатоная 7.</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body style="padding-top: 70px;">
    <nav class="navbar navbar-light bg-light">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Лабораторная 7. Котлин-сервлетс.</a>
      </div>
    </nav>

    <div class="container">
    	<h3>Объем фигуры</h3>
    	<hr/>
          <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
            <li class="nav-item" role="presentation">
              <button class="nav-link active" id="parallelepiped-tab" data-bs-toggle="pill" data-bs-target="#parallelepiped" type="button" role="tab" aria-controls="parallelepiped" aria-selected="true">Паралелепипед</button>
            </li>
            <li class="nav-item" role="presentation">
              <button class="nav-link" id="tetrahedron-tab" data-bs-toggle="pill" data-bs-target="#tetrahedron" type="button" role="tab" aria-controls="tetrahedron" aria-selected="false">Тетраэдр</button>
            </li>
            <li class="nav-item" role="presentation">
              <button class="nav-link" id="cube-tab" data-bs-toggle="pill" data-bs-target="#cube" type="button" role="tab" aria-controls="cube" aria-selected="false">Куб</button>
            </li>
            <li class="nav-item" role="presentation">
              <button class="nav-link" id="sphere-tab" data-bs-toggle="pill" data-bs-target="#sphere" type="button" role="tab" aria-controls="sphere" aria-selected="false">Сфера</button>
            </li>
            <li class="nav-item" role="presentation">
              <button class="nav-link" id="ball-tab" data-bs-toggle="pill" data-bs-target="#ball" type="button" role="tab" aria-controls="ball" aria-selected="false">Шар</button>
            </li>
          </ul>
          <div class="tab-content" id="pills-tabContent">
            <div class="tab-pane fade show active" id="parallelepiped" role="tabpanel" aria-labelledby="parallelepiped-tab">
              <form action="/lab7/hello" method="post">
                <div class="mb-3">
                  <input type="hidden" name="figure_type" class="form-control" id="figure_type" value="паралелепипед"> 
                </div>
                <div class="mb-3">
                  <label for="sidea" class="form-label">Длина стороны а</label>
                  <input type="number" step="any" name="sidea" class="form-control" id="sidea">
                </div>
                <div class="mb-3">
                  <label for="sideb" class="form-label">Длина стороны b</label>
                  <input type="number" step="any" name="sideb" class="form-control" id="sideb">
                </div>
                <div class="mb-3">
                  <label for="sidec" class="form-label">Длина стороны c</label>
                  <input type="number" step="any" name="sidec" class="form-control" id="sidec">
                </div>
                <div class="mb-3">
                  <label for="precision" class="form-label">Количество разрядов после запятой</label>
                  <input type="number" name="precision" class="form-control" id="precision" value=0>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
              </form>
            </div>
            <div class="tab-pane fade" id="tetrahedron" role="tabpanel" aria-labelledby="tetrahedron-tab">
              <form action="/lab7/hello" method="post">
                <div class="mb-3">
                  <input type="hidden" name="figure_type" class="form-control" id="figure_type" value="тетраэдр"> 
                </div>
                <div class="mb-3">
                  <label for="square" class="form-label">Площадь основания</label>
                  <input type="number" step="any" name="square" class="form-control" id="square">
                </div>
                <div class="mb-3">
                  <label for="height" class="form-label">Высота</label>
                  <input type="number" step="any" name="height" class="form-control" id="height">
                </div>
                <div class="mb-3">
                  <label for="precision" class="form-label">Количество разрядов после запятой</label>
                  <input type="number" name="precision" class="form-control" id="precision" value=0>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
              </form>
            </div>
            <div class="tab-pane fade" id="cube" role="tabpanel" aria-labelledby="cube-tab">
              <form action="/lab7/hello" method="post">
                <div class="mb-3">
                  <input type="hidden" name="figure_type" class="form-control" id="figure_type" value="куб"> 
                </div>
                <div class="mb-3">
                  <label for="side" class="form-label">Сторона куба</label>
                  <input type="number" step="any" name="side" class="form-control" id="side">
                </div>
                <div class="mb-3">
                  <label for="precision" class="form-label">Количество разрядов после запятой</label>
                  <input type="number" name="precision" class="form-control" id="precision" value=0>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
              </form>
            </div>
            <div class="tab-pane fade" id="sphere" role="tabpanel" aria-labelledby="sphere-tab">
              <form action="/lab7/hello" method="post">
                <div class="mb-3">
                  <input type="hidden" name="figure_type" class="form-control" id="figure_type" value="сфера"> 
                </div>
                <div class="mb-3">
                  <label for="radius1" class="form-label">Внешний радиус</label>
                  <input type="number" step="any" name="radius1" class="form-control" id="radius1">
                </div>
                <div class="mb-3">
                  <label for="radius2" class="form-label">Внутренний радиус</label>
                  <input type="number" step="any" name="radius2" class="form-control" id="radius2">
                </div>
                <div class="mb-3">
                  <label for="precision" class="form-label">Количество разрядов после запятой</label>
                  <input type="number" name="precision" class="form-control" id="precision" value=0>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
              </form>
            </div>
            <div class="tab-pane fade" id="ball" role="tabpanel" aria-labelledby="ball-tab">
              <form action="/lab7/hello" method="post">
                <div class="mb-3">
                  <input type="hidden" name="figure_type" class="form-control" id="figure_type" value="шар"> 
                </div>
                <div class="mb-3">
                  <label for="radius" class="form-label">Радиус шара</label>
                  <input type="number" step="any" name="radius" class="form-control" id="radius">
                </div>
                <div class="mb-3">
                  <label for="precision" class="form-label">Количество разрядов после запятой</label>
                  <input type="number" name="precision" class="form-control" id="precision" value=0>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
              </form></div>
          </div>
      
    	
    	<div class="row">
    		<div class="col-md-12">
		    	<#if figure??>
            <h3>${figure.figure} : ${figure.volume}</h3>
			  	<#else>
			  		
		    	</#if>
			</div>
    	</div>
    	
    </div>


  </body>
</html>
