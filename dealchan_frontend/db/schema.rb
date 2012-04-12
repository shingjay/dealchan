# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended to check this file into your version control system.

ActiveRecord::Schema.define(:version => 20120412155942) do

  create_table "categories", :force => true do |t|
    t.string   "title"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  create_table "cities", :force => true do |t|
    t.string   "name"
    t.string   "country"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  create_table "deals", :force => true do |t|
    t.boolean  "active",                                 :null => false
    t.integer  "bought",                                 :null => false
    t.float    "currentPrice",                           :null => false
    t.text     "description",      :limit => 2147483647
    t.float    "discount",                               :null => false
    t.string   "extraInformation"
    t.string   "image"
    t.string   "link"
    t.float    "originalPrice",                          :null => false
    t.datetime "pubDate"
    t.float    "saving",                                 :null => false
    t.datetime "timeEnds"
    t.string   "title"
    t.string   "address"
    t.float    "latitude"
    t.float    "longitude"
    t.integer  "category_id"
    t.integer  "city_id"
  end

end
