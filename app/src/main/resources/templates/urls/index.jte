@import hexlet.code.dto.urls.UrlsPage
@import hexlet.code.util.NamedRoutes
@param UrlsPage page

@template.layout.page(
    page = page,
    content = @`
        <section>
            <div class="container-lg mt-5">
                <h2>Сайты</h2>
                <table class="table table-bordered table-hover mt-3">
                    <thead>
                        <tr>
                            <th class="col-1">ID</th>
                            <th>Имя</th>
                            <th class="col-2">Последняя проверка</th>
                            <th class="col-1">Код ответа</th>
                        </tr>
                    </thead>
                    <tbody>
                    @for(var url : page.getUrls())
                        <tr>
                            <td>${url.getId()}</td>
                            <td>
                                <a href="${NamedRoutes.urlPath(url.getId())}">${url.getName()}</a>
                            </td>
                            @if(page.getLastCheck().containsKey(url.getId()))
                                <td>${page.getLastCheck().get(url.getId()).getFormatedTime()}</td>
                                <td>${page.getLastCheck().get(url.getId()).getStatusCode()}</td>
                            @endif
                        </tr>
                    @endfor
                    </tbody>
                </table>
            </div>
        </section>
    `
)
