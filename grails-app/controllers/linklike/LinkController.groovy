package linklike

class LinkController {

    def index() { 
        def links = Link.findAll()
        [ links: links ]
    }

    def create() {
        def link = new Link(title: params.title, url: params.url, tag: params.tag)
        link.save()
        redirect(action: "index")
    }

    def voteUpAction() {
        def searched_link = Link.get(params.id) 
        voteUp(searched_link)
        redirect(action: "index")
    }

    def voteDownAction() {
        def searched_link = Link.get(params.id)
        voteDown(searched_link)
        redirect(action: "index")
    }

    def voteUp(link) {
        if (link.likecounts >= 0) {
            link.likecounts++;
            link.save();
        }
    }

    def voteDown(link) {
        if (link.likecounts > 0) {
            link.likecounts--;
            link.save();
        }
    }
}
