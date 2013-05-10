package linklike



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(LinkController)
class LinkControllerTests {

    void testIndex() {
   		mockDomain(Link,
            [  [title:'Facebook', url:'www.facebook.com', tag:'fb'],
               [title:'Google', url:'www.google.com', tag: 'google'],
               [title:'Amazon', url:'www.amazon.com', tag: 'amazon'],
               [title:'Google [TH]', url:'www.google.co.th', tag: 'google'],
               [title:'Google [UK]', url:'www.google.co.uk', tag: 'google']]
         )
   		def model = controller.index()
   		assert model.links.size() == 5
   	}

   	void testGetVoteAndLink() {
   		mockDomain(Link,
            [  [title:'Facebook', url:'www.facebook.com', tag:'fb'],
               [title:'Google', url:'www.google.com', tag: 'google'],
               [title:'Amazon', url:'www.amazon.com', tag: 'amazon'],
               [title:'Google [TH]', url:'www.google.co.th', tag: 'google'],
               [title:'Google [UK]', url:'www.google.co.uk', tag: 'google']]
         )

   		def googleModel = Link.get(2)
   		assert googleModel != null
   		assertEquals 0, googleModel.likecounts
   		assertEquals 'www.google.com', googleModel.url
   		assertEquals 2, googleModel.id
   	}

   	void testVoteWithParam() {
   		mockDomain(Link,
            [  [title:'Facebook', url:'www.facebook.com', tag:'fb'],
               [title:'Google', url:'www.google.com', tag: 'google'],
               [title:'Amazon', url:'www.amazon.com', tag: 'amazon'],
               [title:'Google [TH]', url:'www.google.co.th', tag: 'google'],
               [title:'Google [UK]', url:'www.google.co.uk', tag: 'google']]
         )

         // Normal Testing
   		def googleModel = Link.get(2)
   		assertEquals googleModel.likecounts, 0
   		controller.voteUp(googleModel)
   		assertEquals googleModel.likecounts, 1
         controller.voteDown(googleModel)
         assertEquals googleModel.likecounts, 0

   	}
}
