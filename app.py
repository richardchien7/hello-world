from flask import Flask, render_template, request, redirect
from models.restaurants import Restaurant
from database import db_session, init_db
from sqlalchemy import desc
from models.histories import Histories
import datetime
from random import choice
app = Flask(__name__)


@app.before_first_request
def init():
    init_db()


@app.teardown_appcontext
def shutdown_session(exception=None):
    db_session.remove()


@app.route('/')
def start():
    now = datetime.datetime.now()

    return render_template('start.html', nav='start', now=now)

@app.route('/draw')
def draw():
    restaurants = Restaurant.query.all()
    if not restaurants:
        return redirect('/create-restaurant')
    random_restaurant = choice(restaurants)
    try:
        restaurant = Restaurant.query.get(random_restaurant.id)
        restaurant.draw += 1

        history = Histories(restaurant_id=restaurant.id)

        db_session.add(history)
        db_session.commit()
    except:
        db_session.rollback()
        return redirect('/')

    now = datetime.datetime.now()

    return render_template('draw.html', restaurant=restaurant, now=now)

@app.route('/create-restaurant', methods=['GET', 'POST'])
def create_restaurant():
    if request.method == 'POST':
        name = request.form.get('name')
        description = request.form.get('description')
        site_url = request.form.get('site_url')
        restaurant = Restaurant(name=name, description=description, site_url=site_url)
        db_session.add(restaurant)
        db_session.commit()
        return redirect('/restaurants')
    return render_template('create_restaurant.html')


@app.route('/restaurants')
def restaurant_list():
    restaurants = Restaurant.query.all()

    return render_template("restaurant.html", nav="restaurant",restaurants=restaurants)
@app.route('/edit-restaurant', methods = ['GET', 'POST'])
def edit_restaurant():
    id = request.args.get('id')
    restaurant = Restaurant.query.filter(Restaurant.id == id).first()
    if request.method == 'POST':
        name = request.form.get('name')
        description = request.form.get('description')
        site_url = request.form.get('site_url')

        restaurant.name = name
        restaurant.description = description
        restaurant.site_url = site_url
        restaurant.modified_time = datetime.datetime.now()

        db_session.commit()

        return redirect("/restaurants")
    return render_template('edit_restaurant.html', restaurant=restaurant)


@app.route('/delete-restaurant')
def delete_restaurant():
    id=request.args.get("id")
    restaurant=Restaurant.query.filter(Restaurant.id == id).first()
    if restaurant:
        db_session.delete(restaurant)
        db_session.commit()
    return redirect('/restaurants')


@app.route('/history')
def history():
    histories = Histories.query.order_by(desc(Histories.created_time)).limit(20)

    return render_template('history.html', nav="history", histories=histories)

@app.route('/top')
def top():
    restaurants = Restaurant.query.order_by('-draw').limit(5)
    return render_template("top.html", nav="top", restaurants=restaurants)


def mealformat(value):
    if value.hour in [4, 5, 6, 7, 8, 9]:
        return 'Breakfast'
    elif value.hour in [10, 11, 12, 13, 14, 15]:
        return 'lunch'
    elif value.hour in [16, 17, 18, 19, 20, 21]:
        return 'dinner'
    else:
        return 'supper'


def datetimeformat(value):
    return value.strftime('%Y-%m-%d %H:%M:%S')


app.jinja_env.filters['datetime'] = datetimeformat
app.jinja_env.filters['meal'] = mealformat
if __name__ == '__main__':
    app.jinja_env.auto_reload = True
    app.run(debug=True)
