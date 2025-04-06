@import gg.jte.Content
@import hexlet.code.util.NamedRoutes
@param Content content

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">

    <title>Page Analyzer</title>
</head>
<body class="d-flex flex-column min-vh-100">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
          <div class="container-fluid">
            <a class="navbar-brand" href="${NamedRoutes.rootPath()}">Анализатор Страниц</a>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav">
                <a class="nav-link" aria-current="page" href="${NamedRoutes.rootPath()}">Главная</a>
                <a class="nav-link" href="#">Сайты</a>
              </div>
            </div>
          </div>
        </nav>
        <main class="flex-grow-1">${content}
        </main>
    <footer class="footer border-top py-4 mt-5 bg-dark">
        <div class="container-xl">
            <div class="text-center text-light">
                created by
                <a class="link-light" href="https://github.com/sergeycherkasovv" target="_blank">Cherkasov Sergey</a>
            </div>
        </div>
    </footer>
</body>
</html>