@import gg.jte.Content
@import hexlet.code.dto.BasePage
@import hexlet.code.util.NamedRoutes
@param Content content
@param BasePage page = null

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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>

    <title>Page Analyzer</title>
</head>
<body class="d-flex flex-column min-vh-100">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
          <div class="container-fluid">
            <a class="navbar-brand" href="${NamedRoutes.rootPath()}">Анализатор Страниц</a>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav">
                <a class="nav-link" aria-current="page" href="${NamedRoutes.rootPath()}">Главная</a>
                <a class="nav-link" href="${NamedRoutes.urlsPath()}">Сайты</a>
              </div>
            </div>
          </div>
        </nav>

        @if(page != null && page.getFlash() != null)
            <div class="rounded-0 m-0 alert alert-dismissible fade show alert-${page.getFlashType()}" role="alert">
                <p class="m-0">${page.getFlash()}</p>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        @endif

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