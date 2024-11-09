import React from 'react'
import '../styles/Home.css';
const Home = () => {
  return (
    <>
<div class="about-page">
        <section class="about-project">
            <h1>What is TTRPG?</h1>
            <p>TTRPG stands for Tabletop Role-Playing Game. 
            It's a kind of role-playing game in which the participants 
            describe their characters' actions through speech and sometimes movements.
            Participants determine the actions of their characters based on their 
            characterization. The actions succeed or fail according to a set system of 
            formal rules and guidelines, usually involving randomization (such as through dice). 
            Within the rules, players have the freedom to improvise
            and their choices shape the direction and outcome of the game.
            </p>
        </section>


        <section class="about-goal">
            <h2>Project Motivation</h2>
            <p>Our mission is to cultivate a welcoming and
                 inclusive community where TTRPG players 
                 can find like-minded individuals, 
                 discover new games, and deepen their love 
                 for storytelling and role-play. 
                 Our primary goals are:</p>
            <p className='bulletPoint'>Accessibility</p>
            <p className='point'> We want to make the process of finding 
            and creating games a lot easier for both the newcomers 
            who are searching for their first matches
            and the seasoned veterans who need a system 
            of organizing their play sessions.</p>
            <p className='bulletPoint'>Connections</p>
            <p className='point'> We want to connect people who have similar interests,
                help them create small communities and encourage them to make new friends.
            </p>
            <p className='bulletPoint'>Events</p>
            <p className='point'> We want to help organizations that have specialized
                themselves for TTRPGs to host large scale events on our site, 
                spread awareness amongst players of their existance 
                and cooperate with them for special promotions.
            </p>
        </section>

        <section class="contact-section">
            <h2>Contact Us</h2>
            <p>Interested in joining our community or have questions? Reach out to us!</p>
            <ul>
                <li>Email: <a href="mailto:info@tabletopconnections.org">info@TTRPG_FINDER.org</a></li>
                <li>Twitter: <a href="https://twitter.com/TTRPG-FINDER">@TTRPG-FINDER</a></li>
                <li>Discord: <a href="https://discord.gg/TTRPG-FINDER">Join our Discord Server</a></li>
            </ul>
        </section>

        <section class="about-features">
            <h2>Key Features</h2>
            <ul>
                <li>Search & Join Games</li>
                <p>You can search for any game by name but also through all sorts of filters
                    that will help you determine the best game just for you!
                </p>
                <li>Host Games</li>
                <p>You can host custom games where you can add your own set of rules or
                    use the preexisting templates. You also control who gets to join the fun,
                    make a form application for people to request coming to your event or keep
                    it simple and allow anyone to join, it's in your hands!
                </p>
                <li>Promote Your Business</li>
                <p>We offer special features for organizations, which include a special 
                    verified business account and a profile page for players to check out 
                    with all the necessary information about the org and the events being held
                    by them.
                </p>
            </ul>
        </section>
</div>
    </>
  )
}

export default Home