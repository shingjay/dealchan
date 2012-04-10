class CreateDeals < ActiveRecord::Migration
  def change
    create_table :deals do |t|
      t.integer :active
      t.integer :bought
      t.string :city
      t.decimal :currentPrice
      t.text :description
      t.integer :discount
      t.string :image
      t.string :link
      t.decimal :originalPrice
      t.datetime :pubDate
      t.decimal :saving
      t.datetime :timeEnds
      t.string :title
      t.string :address
      t.decimal :latitude
      t.decimal :longitude
      t.string :category
      t.string :extraInformation

      t.timestamps
    end
  end
end
